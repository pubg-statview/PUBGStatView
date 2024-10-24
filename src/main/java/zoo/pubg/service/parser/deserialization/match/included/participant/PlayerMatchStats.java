package zoo.pubg.service.parser.deserialization.match.included.participant;

public record PlayerMatchStats(int dbno, int kills, int assists, int headshotKills, float damageDealt,
                               float longestKill) {

}
