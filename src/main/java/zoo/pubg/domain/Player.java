package zoo.pubg.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import zoo.pubg.vo.PlayerName;

@Entity
@Getter
@NoArgsConstructor
public class Player {

    @Id
    private String playerId;

    @Embedded
    private PlayerName name;

    private String shardId;
    private String clanId;
    private LocalDateTime lastUpdated;

    public Player(String playerId, PlayerName name, String shardId, String clanId, LocalDateTime lastUpdated) {
        this.playerId = playerId;
        this.name = name;
        this.shardId = shardId;
        this.clanId = clanId;
        this.lastUpdated = lastUpdated;
    }

    public void updateName(PlayerName newName) {
        this.name = newName;
    }
}
