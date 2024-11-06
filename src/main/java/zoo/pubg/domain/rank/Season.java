package zoo.pubg.domain.rank;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import zoo.pubg.constant.Shards;
import zoo.pubg.vo.SeasonId;

@Entity
@Getter
@AllArgsConstructor
public class Season {

    @EmbeddedId
    @AttributeOverride(name = "id", column = @Column(name = "seasonId"))
    private SeasonId id;
    private Boolean isCurrentSeason;

    @Enumerated(EnumType.STRING)
    private Shards shards;

    public void update(Season other) {
        this.isCurrentSeason = other.isCurrentSeason;
    }
}
