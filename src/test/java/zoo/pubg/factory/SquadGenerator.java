package zoo.pubg.factory;

import java.time.LocalDateTime;
import java.util.List;
import zoo.pubg.domain.Player;
import zoo.pubg.domain.Squad;
import zoo.pubg.vo.PlayerIds;
import zoo.pubg.vo.SquadId;

public class SquadGenerator {

    public static Squad generate(List<Player> players) {
        SquadId squadId = SquadId.from(
                new PlayerIds(players.stream().map(Player::getPlayerId).toList())
        );
        return new Squad(squadId, LocalDateTime.now());
    }
}
