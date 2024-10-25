package zoo.pubg.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Match {

    @Id
    private String matchId;

    private String mapName;
    private String gameMode;
    private String matchType;
    private String shardId;
    private int duration;
    private String telemetryURL;
    private LocalDateTime createdAt;
}
