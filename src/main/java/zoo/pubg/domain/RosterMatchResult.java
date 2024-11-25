package zoo.pubg.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import zoo.pubg.vo.RosterId;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RosterMatchResult {

    @EmbeddedId
    private RosterId rosterId;

    @Column(name = "`rank`")
    private Integer rank;
    private Integer teamId;

    @ManyToOne
    @JoinColumn(name = "matchId", referencedColumnName = "matchId")
    private Match match;

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != RosterMatchResult.class) {
            return false;
        }
        RosterMatchResult other = (RosterMatchResult) o;
        return rosterId.equals(other.getRosterId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(rosterId.getRosterId());
    }
}
