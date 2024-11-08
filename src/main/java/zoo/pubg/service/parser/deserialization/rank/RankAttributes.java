package zoo.pubg.service.parser.deserialization.rank;

import zoo.pubg.constant.GameModeType;
import zoo.pubg.domain.rank.RankedDetails;

public record RankAttributes(RankedGameModeStats rankedGameModeStats) {

    public RankedDetails get(GameModeType type) {
        return rankedGameModeStats.get(type);
    }
}
