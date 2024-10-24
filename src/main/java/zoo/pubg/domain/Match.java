package zoo.pubg.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;

@Entity
@Getter
public class Match {

    @Id
    private String matchId;

    private String mapName;
    private String gameMode;
    private String matchType;
    private int duration;
    private String telemetryURL;
    private LocalDateTime createdAt;
}
