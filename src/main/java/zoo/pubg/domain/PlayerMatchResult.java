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
import zoo.pubg.vo.PlayerMatchId;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    private int dbno;
    private float damageDealt;
    private int headshotKills;
    private int kills;
    private int assists;
    private float longestKill;
    private int winPlace;
}
