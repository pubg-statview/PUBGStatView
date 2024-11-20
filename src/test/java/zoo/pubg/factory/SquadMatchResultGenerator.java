package zoo.pubg.factory;

import zoo.pubg.domain.Match;
import zoo.pubg.domain.Squad;
import zoo.pubg.domain.SquadMatchResult;

public class SquadMatchResultGenerator {

    public static SquadMatchResult generate(Squad squad, Match match) {
        return new SquadMatchResult(
                null, squad, match,
                10, 1000f, 14, 1
        );
    }
}
