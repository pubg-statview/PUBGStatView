package zoo.pubg.dto;

import zoo.pubg.domain.Player;
import zoo.pubg.domain.PlayerMatchResult;

public record PlayerResultDto(Player player, Integer dbno, Float damageDealt, Integer headshotKills, Integer kills,
                              Integer assists, Float longestKill, Integer winPlace) {

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
