package zoo.pubg.service.parser.deserialization.rank;

import zoo.pubg.constant.GameModeType;
import zoo.pubg.domain.rank.RankedDetails;

public record RankData(RankAttributes attributes) {

    public RankedDetails get(GameModeType type) {
        return attributes.get(type);
    }
}
