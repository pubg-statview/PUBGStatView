package zoo.pubg.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Player {

    @Id
    private String playerId;

    private String name;
    private String shardId;
    private String clanId;
    private LocalDateTime lastUpdated;

    public Player(String playerId, String name, String shardId, String clanId, LocalDateTime lastUpdated) {
        this.playerId = playerId;
        this.name = name;
        this.shardId = shardId;
        this.clanId = clanId;
        this.lastUpdated = lastUpdated;
    }
}
