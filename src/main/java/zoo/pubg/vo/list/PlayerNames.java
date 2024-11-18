package zoo.pubg.vo.list;

import java.util.List;
import zoo.pubg.vo.PlayerName;

public class PlayerNames extends ValueList<PlayerName> {

    public PlayerNames() {
        super();
    }

    public PlayerNames(List<PlayerName> playerNames) {
        super(playerNames);
    }

    public static PlayerNames from(List<String> playerNames) {
        return new PlayerNames(
                playerNames.stream().map(PlayerName::new).toList()
        );
    }

    public String joinToString() {
        return String.join(",", list.stream().map(PlayerName::getName).toList());
    }
}
