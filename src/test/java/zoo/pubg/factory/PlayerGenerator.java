package zoo.pubg.factory;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import zoo.pubg.constant.PlayerType;
import zoo.pubg.constant.Shards;
import zoo.pubg.domain.Player;
import zoo.pubg.domain.list.Players;
import zoo.pubg.vo.PlayerId;
import zoo.pubg.vo.PlayerName;

public class PlayerGenerator {
    private static int POSTFIX = 0;

    public static Player generatePlayer() {
        int postfix = POSTFIX++;
        PlayerId playerId = new PlayerId("account." + postfix);
        PlayerName playerName = new PlayerName("testPlayer-" + postfix);
        return new Player(
                playerId, playerName, Shards.KAKAO, "", PlayerType.ETC, LocalDateTime.now()
        );
    }

    public static Players generatePlayers(int count) {
        return new Players(IntStream.range(0, count)
                .mapToObj(i -> generatePlayer())
                .collect(Collectors.toList()));
    }
}
