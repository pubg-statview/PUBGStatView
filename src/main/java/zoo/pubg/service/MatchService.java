package zoo.pubg.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.constant.Shards;
import zoo.pubg.domain.Match;
import zoo.pubg.repository.MatchRepository;
import zoo.pubg.service.api.PubgBasicApi;
import zoo.pubg.service.dto.DeserializedMatchDto;
import zoo.pubg.service.dto.IncludedDto;
import zoo.pubg.service.dto.MatchDataDto;
import zoo.pubg.service.parser.MatchApiParser;
import zoo.pubg.service.parser.deserialization.match.MatchInformation;
import zoo.pubg.vo.MatchId;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MatchService {

    private final static MatchApiParser parser = new MatchApiParser();

    private final PubgBasicApi pubgBasicApi;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private PlayerMatchService playerMatchService;

    @Transactional
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
        playerMatchService.fetch(match, includedDto.participantDtos());
    }

    private DeserializedMatchDto deserialize(String response) throws JsonProcessingException {
        MatchInformation result = parser.parse(response);
        return result.toDto();
    }
}
