package zoo.pubg.service.parser.deserialization.season;

import java.util.List;
import zoo.pubg.constant.Shards;
import zoo.pubg.domain.rank.Season;
import zoo.pubg.service.parser.deserialization.season.data.SeasonData;

public record SeasonDeserializer(List<SeasonData> data) {

    public List<Season> toEntities(Shards shards) {
        return data.stream().map(seasonData -> seasonData.toEntity(shards)).toList();
    }
}
