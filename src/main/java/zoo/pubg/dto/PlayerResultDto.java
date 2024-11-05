package zoo.pubg.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import zoo.pubg.domain.Player;
import zoo.pubg.domain.PlayerMatchResult;

@RequiredArgsConstructor
@Getter
public class PlayerResultDto {

    private final Player player;
    private final Integer dbno;
    private final Float damageDealt;
    private final Integer headshotKills;
    private final Integer kills;
    private final Integer assists;
    private final Float longestKill;
    private final Integer winPlace;

    public static PlayerResultDto from(PlayerMatchResult playerMatchResult) {
        return new PlayerResultDto(
                playerMatchResult.getPlayer(),
                playerMatchResult.getDbno(), playerMatchResult.getDamageDealt(),
                playerMatchResult.getHeadshotKills(), playerMatchResult.getKills(),
                playerMatchResult.getAssists(), playerMatchResult.getLongestKill(),
                playerMatchResult.getWinPlace()
        );
    }
}
