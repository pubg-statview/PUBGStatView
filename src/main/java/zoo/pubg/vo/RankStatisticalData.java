package zoo.pubg.vo;

import jakarta.persistence.Embeddable;

@Embeddable
public record RankStatisticalData(
        Integer roundsPlayed,
        Float avgRank,
        Float top10Ratio,
        Float winRatio,
        Integer assists,
        Integer wins,
        Float kda,
        Integer kills,
        Integer deaths,
        Float damageDealt,
        Integer dBNOs
) {
}
