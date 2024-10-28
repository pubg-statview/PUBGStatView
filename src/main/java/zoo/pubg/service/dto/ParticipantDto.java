package zoo.pubg.service.dto;

import zoo.pubg.vo.PlayerId;

public record ParticipantDto(
        String playerMatchId, PlayerId playerId,
        int dbno, int assists, int kills, int headshotKills,
        float damageDealt, float longestKill, int winPlace
) {
}
