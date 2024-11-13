package zoo.pubg.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import zoo.pubg.constant.PlayerType;
import zoo.pubg.constant.Shards;
import zoo.pubg.vo.PlayerId;
import zoo.pubg.vo.PlayerName;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @EmbeddedId
    private PlayerId playerId;

    @Embedded
    private PlayerName name;

    @Enumerated(EnumType.STRING)
    private Shards shardId;
    private String clanId;

    @Enumerated(EnumType.STRING)
    private PlayerType playerType;

    private LocalDateTime lastUpdated;

    public void updateName(PlayerName newName) {
        this.name = newName;
    }

    public void updateAll(Player other) {
        this.name = other.name;
        this.shardId = other.shardId;
        this.clanId = other.clanId;
        this.lastUpdated = other.lastUpdated;
    }

    public boolean hasSameId(Player other) {
        return playerId.equals(other.getPlayerId());
    }
}
