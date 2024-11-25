package zoo.pubg.vo;

import jakarta.persistence.Embeddable;
import java.util.List;
import zoo.pubg.domain.PlayerMatchResult;

@Embeddable
public record SquadMatchStatisticalData(
        Integer totalDbno,
        Float totalDamageDealt,
        Integer totalKills,
        Integer winPlace
) {
    public static SquadMatchStatisticalData from(List<PlayerMatchResult> results) {
        if (results.isEmpty()) {
            throw new IllegalStateException();
        }
        int totalDbno = 0, totalKills = 0, winPlace = results.get(0).getWinPlace();
        float totalDamageDealt = 0;
        for (PlayerMatchResult result : results) {
            totalDbno += result.getDbno();
            totalKills += result.getKills();
            totalDamageDealt += result.getDamageDealt();
        }
        return new SquadMatchStatisticalData(
                totalDbno, totalDamageDealt, totalKills, winPlace
        );
    }
}
