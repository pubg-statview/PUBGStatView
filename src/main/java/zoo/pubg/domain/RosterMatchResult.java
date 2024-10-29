package zoo.pubg.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import zoo.pubg.vo.PlayerMatchId;

@Entity
public class RosterMatchResult {

    @Id
    private String rosterId;

    private Integer rank;
    private Integer teamId;
    private PlayerMatchId playerMatchId;

    @ManyToOne
    @JoinColumn(name = "matchId", referencedColumnName = "matchId")
    private Match match;
}
