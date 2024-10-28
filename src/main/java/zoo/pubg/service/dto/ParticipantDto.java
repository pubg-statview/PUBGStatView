package zoo.pubg.service.dto;

import zoo.pubg.vo.PlayerId;
import zoo.pubg.vo.PlayerMatchId;

public record ParticipantDto(
        PlayerMatchId playerMatchId, PlayerId playerId,
        int dbno, int assists, int kills, int headshotKills,
        float damageDealt, float longestKill, int winPlace
) {
}
