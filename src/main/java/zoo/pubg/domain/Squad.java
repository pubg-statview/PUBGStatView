package zoo.pubg.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import zoo.pubg.constant.LastUpdated;
import zoo.pubg.vo.SquadId;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Squad {

    @EmbeddedId
    private SquadId squadId;

    private LocalDateTime lastUpdated;

    public Squad(SquadId squadId) {
        this.squadId = squadId;
        this.lastUpdated = LastUpdated.INITIALIZATION.getTime();
    }

    public void update() {
        this.lastUpdated = LocalDateTime.now();
    }
}
