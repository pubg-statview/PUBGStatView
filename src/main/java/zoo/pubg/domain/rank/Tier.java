package zoo.pubg.domain.rank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Tier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tierId")
    private Long id;

    private String currentTier;
    private String currentTierSub;
    private Integer currentRankPoint;
    private String bestTier;
    private String bestTierSub;
    private Integer bestRankPoint;
}
