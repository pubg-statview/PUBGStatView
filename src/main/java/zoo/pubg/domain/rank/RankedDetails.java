package zoo.pubg.domain.rank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class RankedDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rankedDetailsId")
    private Long Id;

    private Integer roundsPlayed;
    private Float avgRank;
    private Float top10Ratio;
    private Float winRatio;
    private Integer assists;
    private Integer wins;
    private Float kda;
    private Integer kills;
    private Integer deaths;
    private Float damageDealts;
    private Integer dbnos;

    @OneToOne
    @JoinColumn(name = "tierId", referencedColumnName = "tierId")
    private Tier tier;
}
