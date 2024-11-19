package zoo.pubg.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.constant.Shards;
import zoo.pubg.domain.Player;
import zoo.pubg.domain.rank.Rank;
import zoo.pubg.domain.rank.Season;
import zoo.pubg.repository.RankRepository;
import zoo.pubg.service.api.PubgBasicApi;
import zoo.pubg.service.dto.RankedDetailsDto;
import zoo.pubg.service.parser.RankApiParser;
import zoo.pubg.service.parser.deserialization.rank.RankDeserializer;

@Service
@Transactional
@RequiredArgsConstructor
public class RankService {

    private final static RankApiParser parser = new RankApiParser();

    private final PubgBasicApi pubgBasicApi;

    @Autowired
    private RankRepository rankRepository;

    @Autowired
    private SeasonService seasonService;

    public void fetch(Shards shards, Player player, Season season) throws JsonProcessingException {
        String responseString = pubgBasicApi.fetchRank(shards.getShardName(), player.getPlayerId().getPlayerId(),
                season.getSeasonId().getId());
        RankDeserializer rankDeserializer = parser.parse(responseString);
        List<RankedDetailsDto> rankedDetailsDtos = rankDeserializer.getAllDetails();

        for (RankedDetailsDto rankedDetailsDto : rankedDetailsDtos) {
            Rank rank = Rank.builder()
                    .season(season)
                    .player(player)
                    .gameMode(rankedDetailsDto.type())
                    .rankedDetails(rankedDetailsDto.details())
                    .build();
            rankRepository.save(rank);
        }
    }

    public void fetchCurrentSeasonRank(Shards shards, Player player) throws JsonProcessingException {
        Season currentSeason = seasonService.getCurrentSeason(shards);
        fetch(shards, player, currentSeason);
    }
}
