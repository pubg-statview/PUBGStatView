package zoo.pubg.service.dto;

public record ParticipantDto(
        String playerMatchId, String playerId,
        int dbno, int assists, int kills, int headshotKills,
        float damageDealt, float longestKill
) {
}
