package zoo.pubg.service.parser.deserialization.match.included.participant;

import zoo.pubg.vo.PlayerId;

public record PlayerMatchStats(
        PlayerId playerId, int dbno, int kills, int assists,
        int headshotKills, float damageDealt, float longestKill, int winPlace) {

}
