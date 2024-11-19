package zoo.pubg.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import zoo.pubg.vo.PlayerId;
import zoo.pubg.vo.SquadId;
import zoo.pubg.vo.SquadPlayerId;

@Entity
@Getter
@NoArgsConstructor
public class SquadPlayer {

    @EmbeddedId
    private SquadPlayerId squadPlayerId;

    @ManyToOne
    @MapsId("squadId")
    @JoinColumn(name = "squadId", referencedColumnName = "squadId")
    private Squad squad;

    @ManyToOne
    @MapsId("playerId")
    @JoinColumn(name = "playerId", referencedColumnName = "playerId")
    private Player player;

    public SquadPlayer(Squad squad, Player player) {
        this.squadPlayerId = new SquadPlayerId(squad.getSquadId(), player.getPlayerId());
        this.squad = squad;
        this.player = player;
    }

    private SquadId getSquadId() {
        return squadPlayerId.getSquadId();
    }

    private PlayerId getPlayerId() {
        return squadPlayerId.getPlayerId();
    }
}
