package zoo.pubg.service.parser.deserialization.match.included.participant;

public record PlayerMatchStats(
        String playerId, int dbno, int kills, int assists,
        int headshotKills, float damageDealt, float longestKill, int winPlace) {

}
