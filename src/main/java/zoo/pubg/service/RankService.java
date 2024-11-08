package zoo.pubg.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.constant.Shards;
import zoo.pubg.domain.Player;
import zoo.pubg.domain.rank.Season;
import zoo.pubg.repository.RankRepository;
import zoo.pubg.service.api.PubgBasicApi;
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

    public void fetch(Shards shards, Player player, Season season) throws JsonProcessingException {
        String responseString = pubgBasicApi.fetchRank(shards.getShardName(), player.getPlayerId().getPlayerId(),
                season.getSeasonId().getId());
        RankDeserializer rankDeserializer = parser.parse((responseString));
    }
}
