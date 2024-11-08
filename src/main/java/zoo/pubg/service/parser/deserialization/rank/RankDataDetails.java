package zoo.pubg.service.parser.deserialization.rank;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import zoo.pubg.vo.RankStatisticalData;

@Getter
@NoArgsConstructor
@ToString
public class RankDataDetails {

    private Tier currentTier;
    private Integer currentRankPoint;

    private Tier bestTier;
    private Integer bestRankPoint;

    @JsonUnwrapped
    private RankStatisticalData statisticalData;
}
