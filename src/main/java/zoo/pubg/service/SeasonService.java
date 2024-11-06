package zoo.pubg.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.constant.Shards;
import zoo.pubg.domain.rank.Season;
import zoo.pubg.exception.NotFoundException;
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

    public Season getCurrentSeason(Shards shards) {
        return seasonRepository.findCurrentSeason(shards);
    }

    @Transactional
    public void fetch(Shards shards) throws JsonProcessingException {
        String response = pubgBasicApi.fetchSeasons(shards.getShardName());
        SeasonDeserializer deserializer = parser.parse(response);
        List<Season> entities = deserializer.toEntities(shards);
        List<Season> seasons = seasonRepository.findAll(shards);
        Season currentSeason = seasonRepository.findCurrentSeason(shards);
        Season currentSeasonFromApi = findCurrentSeason(entities);

        if (seasons.isEmpty() || currentSeason == null) {
            for (Season entity : entities) {
                seasonRepository.save(entity);
            }
            return;
        }

        if (!currentSeason.hasSameSeasonId(currentSeasonFromApi)) {
            currentSeason.update(false);
            seasonRepository.save(currentSeasonFromApi);
        }
    }

    private Season findCurrentSeason(List<Season> seasons) {
        return seasons.stream()
                .filter(Season::getIsCurrentSeason)
                .findAny()
                .orElseThrow(NotFoundException::new);
    }
}
