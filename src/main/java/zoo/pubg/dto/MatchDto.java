package zoo.pubg.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import zoo.pubg.domain.Match;

@RequiredArgsConstructor
@Getter
public class MatchDto {

    private final String mapName;
    private final String gameMode;
    private final String matchType;
    private final Integer Duration;
    private final LocalDateTime createdAt;

    public static MatchDto from(Match match) {
        return new MatchDto(
                match.getMapName(), match.getGameMode(), match.getMatchType(),
                match.getDuration(), match.getCreatedAt()
        );
    }
}
