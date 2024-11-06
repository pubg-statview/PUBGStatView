package zoo.pubg.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.constant.Shards;
import zoo.pubg.domain.rank.Season;
import zoo.pubg.repository.SeasonRepository;
import zoo.pubg.service.api.PubgBasicApi;
import zoo.pubg.service.parser.SeasonApiParser;
import zoo.pubg.service.parser.deserialization.season.SeasonDeserializer;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SeasonService {

    private final static SeasonApiParser parser = new SeasonApiParser();

    private final PubgBasicApi pubgBasicApi;

    @Autowired
    private SeasonRepository seasonRepository;

    public Season getCurrentSeason() {
        return seasonRepository.findCurrentSeason();
    }

    @Transactional
    public void fetch(Shards shards) throws JsonProcessingException {
        String response = pubgBasicApi.fetchSeasons(shards.getShardName());
        SeasonDeserializer deserializer = parser.parse(response);
        List<Season> entities = deserializer.toEntities(shards);
        entities.forEach(entity -> seasonRepository.save(entity));
    }
}