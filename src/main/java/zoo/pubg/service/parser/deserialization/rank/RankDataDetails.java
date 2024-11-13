package zoo.pubg.service.parser.deserialization.rank;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import zoo.pubg.domain.rank.RankedDetails;
import zoo.pubg.domain.rank.Tier;
import zoo.pubg.vo.RankStatisticalData;

@Getter
@NoArgsConstructor
@ToString
public class RankDataDetails {

    private TierDeserializer currentTier;
    private Integer currentRankPoint;

    private TierDeserializer bestTier;
    private Integer bestRankPoint;

    @JsonUnwrapped
    private RankStatisticalData statisticalData;

    public RankedDetails toEntity() {
        return new RankedDetails(
                statisticalData,
                Tier.builder()
                        .currentTier(currentTier.getTier())
                        .currentTierSub(currentTier.getSubTier())
                        .currentRankPoint(currentRankPoint)
                        .bestTier(bestTier.getTier())
                        .bestTierSub(bestTier.getSubTier())
                        .bestRankPoint(bestRankPoint)
                        .build()
        );
    }
}
