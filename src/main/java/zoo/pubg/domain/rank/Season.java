package zoo.pubg.domain.rank;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import zoo.pubg.vo.SeasonId;

@Entity
@Getter
@AllArgsConstructor
public class Season {

    @EmbeddedId
    @AttributeOverride(name = "id", column = @Column(name = "seasonId"))
    private SeasonId id;
    private Boolean isCurrentSeason;
}
