package zoo.pubg.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import zoo.pubg.constant.Shards;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`Match`")
public class Match {

    @Id
    private String matchId;

    private String mapName;
    private String gameMode;
    private String matchType;

    @Enumerated(EnumType.STRING)
    private Shards shardId;
    private int duration;
    private String telemetryURL;
    private LocalDateTime createdAt;
}
