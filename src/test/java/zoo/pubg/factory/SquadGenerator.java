package zoo.pubg.factory;

import zoo.pubg.domain.Squad;
import zoo.pubg.domain.list.Players;
import zoo.pubg.vo.SquadId;

public class SquadGenerator {

    public static Squad generate(Players players) {
        SquadId squadId = SquadId.from(
                players.getPlayerIds()
        );
        return new Squad(squadId);
    }
}
