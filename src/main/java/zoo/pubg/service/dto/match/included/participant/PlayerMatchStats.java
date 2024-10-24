package zoo.pubg.service.dto.match.included.participant;

public record PlayerMatchStats(int dbno, int kills, int assists, int headshotKills, float damageDealt,
                               float longestKill) {

}
