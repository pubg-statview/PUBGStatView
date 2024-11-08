package zoo.pubg.service.parser.deserialization.rank;

import zoo.pubg.constant.GameModeType;
import zoo.pubg.domain.rank.RankedDetails;

public record RankDeserializer(RankData data) {

    public RankedDetails get(GameModeType type) {
        return data.get(type);
    }
}
