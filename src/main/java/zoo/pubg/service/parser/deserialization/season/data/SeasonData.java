package zoo.pubg.service.parser.deserialization.season.data;

import zoo.pubg.constant.Shards;
import zoo.pubg.domain.rank.Season;
import zoo.pubg.vo.SeasonId;

public record SeasonData(SeasonId id, SeasonAttributes attributes) {

    public Season toEntity(Shards shards) {
        return new Season(id, attributes.isCurrentSeason(), shards);
    }
}
