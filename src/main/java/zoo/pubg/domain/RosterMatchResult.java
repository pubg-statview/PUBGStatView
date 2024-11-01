package zoo.pubg.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import zoo.pubg.vo.RosterId;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RosterMatchResult {

    @EmbeddedId
    private RosterId rosterId;

    @Column(name = "`rank`")
    private Integer rank;
    private Integer teamId;

    @ManyToOne
    @JoinColumn(name = "matchId", referencedColumnName = "matchId")
    private Match match;
}
