package zoo.pubg.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import zoo.pubg.vo.SquadId;

@Entity
@Getter
public class Squad {

    private SquadId squadId;
}
