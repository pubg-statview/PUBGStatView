package zoo.pubg.factory;

import java.util.ArrayList;
import java.util.List;
import zoo.pubg.domain.Match;
import zoo.pubg.domain.Player;
import zoo.pubg.domain.PlayerMatchResult;
import zoo.pubg.domain.RosterMatchResult;
import zoo.pubg.domain.list.Players;
import zoo.pubg.vo.PlayerMatchId;

public class PlayerMatchResultGenerator {
    private static int POSTFIX = 0;

    public static PlayerMatchResult generate(
            Player player, Match match, RosterMatchResult rosterMatchResult) {
        int postfix = POSTFIX++;
        return new PlayerMatchResult(
                new PlayerMatchId("testPlayerMatchId-" + postfix), player, match, rosterMatchResult,
                0, 0f, 0, 0, 0, 0f, rosterMatchResult.getRank()
        );
    }

    public static List<PlayerMatchResult> generateResults(
            Players players, Match match, RosterMatchResult rosterMatchResult) {
        List<PlayerMatchResult> list = new ArrayList<>();
        for (Player player : players) {
            list.add(generate(player, match, rosterMatchResult));
        }
        return list;
    }
}
