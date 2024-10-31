package zoo.pubg.service.parser.deserialization.match.included.participant;

import zoo.pubg.vo.PlayerId;
import zoo.pubg.vo.PlayerName;

public record PlayerMatchStats(
        PlayerId playerId, PlayerName name, int dbno, int kills, int assists,
        int headshotKills, float damageDealt, float longestKill, int winPlace) {

}
