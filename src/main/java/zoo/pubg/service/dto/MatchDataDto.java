package zoo.pubg.service.dto;

import java.time.LocalDateTime;
import zoo.pubg.constant.Shards;

public record MatchDataDto(
        String matchId, String mapName, String gameMode, String matchType,
        Shards shardId, int duration, LocalDateTime createdAt
) {
}
