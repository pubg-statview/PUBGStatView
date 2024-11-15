package zoo.pubg.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import zoo.pubg.vo.PlayerId;
import zoo.pubg.vo.SquadId;
import zoo.pubg.vo.SquadPlayerId;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SquadPlayer {

    @EmbeddedId
    private SquadPlayerId squadPlayerId;

    private PlayerId getPlayerId() {
        return squadPlayerId.getPlayerId();
    }

    private SquadId getSquadId() {
        return squadPlayerId.getSquadId();
    }
}
