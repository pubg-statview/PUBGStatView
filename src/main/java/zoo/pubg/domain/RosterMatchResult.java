package zoo.pubg.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import zoo.pubg.vo.RosterId;

@Entity
public class RosterMatchResult {

    @EmbeddedId
    private RosterId rosterId;

    private Integer rank;
    private Integer teamId;

    @ManyToOne
    @JoinColumn(name = "matchId", referencedColumnName = "matchId")
    private Match match;
}
