package zoo.pubg.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import zoo.pubg.vo.SquadMatchStatisticalData;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SquadMatchResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "squadMatchId")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "squadId", referencedColumnName = "squadId")
    private Squad squad;

    @ManyToOne
    @JoinColumn(name = "matchId", referencedColumnName = "matchId")
    private Match match;

    @Embedded
    private SquadMatchStatisticalData data;
}
