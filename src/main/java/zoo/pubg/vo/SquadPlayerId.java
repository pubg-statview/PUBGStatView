package zoo.pubg.vo;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.Getter;

@Embeddable
@Getter
public class SquadPlayerId implements Serializable {

    private PlayerId playerId;
    private SquadId squadId;
}
