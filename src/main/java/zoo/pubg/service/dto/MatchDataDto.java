package zoo.pubg.service.dto;

import java.time.LocalDateTime;

public record MatchDataDto(
        String matchId, String mapName, String gameMode, String matchType,
        int duration, LocalDateTime createdAt
) {
}
