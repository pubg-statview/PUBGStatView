package zoo.pubg.domain.rank;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import zoo.pubg.vo.RankStatisticalData;

@Entity
@Getter
@NoArgsConstructor
public class RankedDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rankedDetailsId")
    private Long Id;

    @Embedded
    private RankStatisticalData rankStatisticalData;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tierId", referencedColumnName = "tierId")
    private Tier tier;
}
