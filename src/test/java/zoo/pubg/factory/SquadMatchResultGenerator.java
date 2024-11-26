package zoo.pubg.factory;

import zoo.pubg.domain.Match;
import zoo.pubg.domain.Squad;
import zoo.pubg.domain.SquadMatchResult;
import zoo.pubg.vo.SquadMatchStatisticalData;

public class SquadMatchResultGenerator {

    public static SquadMatchResult generate(Squad squad, Match match) {
        SquadMatchStatisticalData data = new SquadMatchStatisticalData(
                10, 1000f, 14, 1
        );
        return new SquadMatchResult(
                squad, match, data
        );
    }
}
