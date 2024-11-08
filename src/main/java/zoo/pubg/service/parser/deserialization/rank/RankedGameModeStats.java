package zoo.pubg.service.parser.deserialization.rank;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.HashMap;
import java.util.Map;
import lombok.ToString;
import zoo.pubg.constant.GameModeType;
import zoo.pubg.domain.rank.RankedDetails;

@ToString
public class RankedGameModeStats {
    private final Map<GameModeType, RankDataDetails> rankedGameModeStats = new HashMap<>();

    @JsonAnyGetter
    public Map<GameModeType, RankDataDetails> getRankedGameModeStats() {
        return rankedGameModeStats;
    }

    @JsonAnySetter
    public void setRankedGameModeStat(String key, RankDataDetails value) {
        GameModeType gameModeType = GameModeType.of(key);
        this.rankedGameModeStats.put(gameModeType, value);
    }

    public RankedDetails get(GameModeType key) {
        if (!rankedGameModeStats.containsKey(key)) {
            return null;
        }
        return rankedGameModeStats.get(key).toEntity();
    }
}
