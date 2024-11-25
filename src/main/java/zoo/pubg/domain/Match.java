package zoo.pubg.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import zoo.pubg.constant.Shards;
import zoo.pubg.vo.MatchId;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`Match`")
@ToString
public class Match {

    @EmbeddedId
    private MatchId matchId;

    private String mapName;
    private String gameMode;
    private String matchType;

    @Enumerated(EnumType.STRING)
    private Shards shardId;
    private Integer duration;
    private String telemetryURL;
    private LocalDateTime createdAt;
}
