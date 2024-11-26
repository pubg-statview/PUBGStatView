package zoo.pubg.factory;

import zoo.pubg.domain.Match;
import zoo.pubg.domain.RosterMatchResult;
import zoo.pubg.vo.RosterId;

public class RosterMatchResultGenerator {
    private static int POSTFIX = 0;

    public static RosterMatchResult generate(Match match) {
        int postfix = POSTFIX++;
        return new RosterMatchResult(
                new RosterId("testRosterId-" + postfix),
                postfix + 1, postfix + 1, match
        );
    }
}
