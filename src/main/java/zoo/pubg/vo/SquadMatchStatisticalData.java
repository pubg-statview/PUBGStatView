package zoo.pubg.vo;

import jakarta.persistence.Embeddable;

@Embeddable
public record SquadMatchStatisticalData(
        Integer totalDbno,
        Float totalDamageDealt,
        Integer totalKills,
        Integer winPlace
) {
}
