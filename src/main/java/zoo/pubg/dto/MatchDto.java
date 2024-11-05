package zoo.pubg.dto;

import java.time.LocalDateTime;
import zoo.pubg.domain.Match;

public record MatchDto(String mapName, String gameMode, String matchType, Integer duration, LocalDateTime createdAt) {

    public static MatchDto from(Match match) {
        return new MatchDto(
                match.getMapName(), match.getGameMode(), match.getMatchType(),
                match.getDuration(), match.getCreatedAt()
        );
    }
}
