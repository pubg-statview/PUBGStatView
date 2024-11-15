package zoo.pubg.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.constant.Shards;
import zoo.pubg.domain.Match;
import zoo.pubg.dto.MatchIdsDto;
import zoo.pubg.repository.MatchRepository;
import zoo.pubg.service.api.PubgBasicApi;
import zoo.pubg.service.dto.DeserializedMatchDto;
import zoo.pubg.service.dto.IncludedDto;
import zoo.pubg.service.dto.MatchDataDto;
import zoo.pubg.service.map.PlayerRosterMap;
import zoo.pubg.service.parser.MatchApiParser;
import zoo.pubg.service.parser.deserialization.match.MatchInformation;
import zoo.pubg.vo.MatchId;

@Service
@Transactional
@RequiredArgsConstructor
public class MatchService {

    private final static MatchApiParser parser = new MatchApiParser();

    private final PubgBasicApi pubgBasicApi;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private RosterMatchService rosterMatchService;

    @Autowired
    private PlayerMatchService playerMatchService;

    public void fetchMatches(MatchIdsDto matchIdsDto) {
        Shards shards = matchIdsDto.shards();
        for (MatchId matchId : matchIdsDto.matchIds()) {
            try {
                fetchMatchHistory(shards, matchId);
            } catch (JsonProcessingException ignore) {
            }
        }
    }

    public void fetchMatchHistory(Shards shards, MatchId matchId) throws JsonProcessingException {
        if (matchRepository.isExists(matchId)) {
            return;
        }
        String response = pubgBasicApi.fetchPlayerMatch(shards.getShardName(), matchId.getMatchId());
        DeserializedMatchDto deserializedMatchDto = deserialize(response);
        MatchDataDto matchDataDto = deserializedMatchDto.matchDataDto();
        IncludedDto includedDto = deserializedMatchDto.includedDto();

        Match match = new Match(
                matchId, matchDataDto.mapName(),
                matchDataDto.gameMode(), matchDataDto.matchType(),
                matchDataDto.shardId(), matchDataDto.duration(),
                deserializedMatchDto.getTelemetryUrl(), matchDataDto.createdAt()
        );
        matchRepository.save(match);
        PlayerRosterMap playerRosterMap = rosterMatchService.fetch(match, includedDto.rosterDtos());
        playerMatchService.fetch(match, includedDto.participantDtos(), playerRosterMap);
    }

    private DeserializedMatchDto deserialize(String response) throws JsonProcessingException {
        MatchInformation result = parser.parse(response);
        return result.toDto();
    }
}
