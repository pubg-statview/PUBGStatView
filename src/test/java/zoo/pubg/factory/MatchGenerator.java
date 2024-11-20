package zoo.pubg.factory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import zoo.pubg.constant.Shards;
import zoo.pubg.domain.Match;
import zoo.pubg.vo.MatchId;

public class MatchGenerator {
    private static int POSTFIX = 0;

    public static Match generateMatch() {
        int postfix = POSTFIX++;
        MatchId matchId = new MatchId("testMatch-" + postfix);

        return new Match(
                matchId, "testMap", "testGameMode", "testMatch", Shards.KAKAO,
                1000, "testurl", LocalDateTime.now()
        );
    }

    public static List<Match> generateMatches(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> generateMatch())
                .collect(Collectors.toList());
    }
}
