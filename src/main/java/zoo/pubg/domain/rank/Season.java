package zoo.pubg.domain.rank;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import zoo.pubg.constant.Shards;
import zoo.pubg.vo.SeasonId;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "seasonId"))
    private SeasonId seasonId;

    @Enumerated(EnumType.STRING)
    private Shards shards;

    private Boolean isCurrentSeason;

    public Season(SeasonId seasonId, Boolean isCurrentSeason, Shards shards) {
        this.seasonId = seasonId;
        this.shards = shards;
        this.isCurrentSeason = isCurrentSeason;
    }

    public void update(boolean isCurrentSeason) {
        this.isCurrentSeason = isCurrentSeason;
    }

    public boolean hasSameSeasonId(Season other) {
        return seasonId.equals(other.seasonId);
    }
}
