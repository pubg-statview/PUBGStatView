package zoo.pubg.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class PlayerMatchResult {

    @Id
    private String playerMatchResultId;

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
    private float longestKill;
    private int winPlace;
}
