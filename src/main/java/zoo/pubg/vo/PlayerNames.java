package zoo.pubg.vo;

import java.util.List;

public record PlayerNames(List<PlayerName> playerNames) {

    public static PlayerNames from(List<String> playerNames) {
        return new PlayerNames(
                playerNames.stream().map(PlayerName::new).toList()
        );
    }
}
