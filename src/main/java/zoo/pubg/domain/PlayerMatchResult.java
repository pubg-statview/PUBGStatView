package zoo.pubg.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import zoo.pubg.vo.PlayerId;
import zoo.pubg.vo.PlayerMatchId;
import zoo.pubg.vo.RosterId;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PlayerMatchResult {

    @EmbeddedId
    @AttributeOverride(name = "playerMatchId", column = @Column(name = "playerMatchResultId"))
    private PlayerMatchId playerMatchResultId;

    @ManyToOne
    @JoinColumn(name = "playerId", referencedColumnName = "playerId")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "matchId", referencedColumnName = "matchId")
    private Match match;

    @ManyToOne
    @JoinColumn(name = "rosterId", referencedColumnName = "rosterId")
    private RosterMatchResult rosterMatchResult;

    private Integer dbno;
    private Float damageDealt;
    private Integer headshotKills;
    private Integer kills;
    private Integer assists;
    private Float longestKill;
    private Integer winPlace;

    public PlayerId getPlayerId() {
        return player.getPlayerId();
    }

    public RosterId getRosterId() {
        return rosterMatchResult.getRosterId();
    }
}
