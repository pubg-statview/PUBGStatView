package zoo.pubg.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
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
    @ManyToOne
    @JoinColumn(name = "squadId", referencedColumnName = "squadId")
    private Squad squad;

    @Id
    @ManyToOne
    @JoinColumn(name = "matchId", referencedColumnName = "matchId")
    private Match match;

    @Embedded
    private SquadMatchStatisticalData data;
}
