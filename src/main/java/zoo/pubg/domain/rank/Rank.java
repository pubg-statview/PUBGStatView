package zoo.pubg.domain.rank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import zoo.pubg.domain.Player;

@Entity
@Getter
@Table(name = "`RankedStats`")
@NoArgsConstructor
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rankedStatsId")
    private Long id;

    private String gameMode;

    @ManyToOne
    @JoinColumn(name = "seasonId", referencedColumnName = "seasonId")
    private Season season;

    @OneToOne
    @JoinColumn(name = "rankedDetailsId", referencedColumnName = "rankedDetailsId")
    private RankedDetails rankedDetails;

    @ManyToOne
    @JoinColumn(name = "playerId", referencedColumnName = "playerId")
    private Player player;
}
