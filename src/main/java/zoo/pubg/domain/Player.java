package zoo.pubg.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import zoo.pubg.constant.PlayerType;
import zoo.pubg.constant.Shards;
import zoo.pubg.vo.PlayerId;
import zoo.pubg.vo.PlayerName;

@Entity
@Getter
@NoArgsConstructor
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

    @OneToMany(mappedBy = "player")
    private final List<PlayerMatchResult> playerMatchResults = new ArrayList<>();

    public Player(PlayerId playerId, PlayerName name, Shards shardId, String clanId, PlayerType playerType,
                  LocalDateTime lastUpdated) {
        this.playerId = playerId;
        this.name = name;
        this.shardId = shardId;
        this.clanId = clanId;
        this.playerType = playerType;
        this.lastUpdated = lastUpdated;
    }

    public void updateName(PlayerName newName) {
        this.name = newName;
    }
}
