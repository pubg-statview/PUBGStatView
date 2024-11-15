package zoo.pubg.vo;

import java.util.ArrayList;
import java.util.List;

public record PlayerNames(List<PlayerName> playerNames) {

    public static PlayerNames from(List<String> playerNames) {
        return new PlayerNames(
                playerNames.stream().map(PlayerName::new).toList()
        );
    }

    public static PlayerNames emptyList() {
        return new PlayerNames(
                new ArrayList<>()
        );
    }

    public String joinToString() {
        return String.join(",", playerNames.stream().map(PlayerName::getName).toList());
    }

    public void add(PlayerName playerName) {
        playerNames.add(playerName);
    }

    public boolean isEmpty() {
        return playerNames.isEmpty();
    }

    public boolean contains(PlayerName playerName) {
        return playerNames.contains(playerName);
    }

    public int size() {
        return playerNames.size();
    }
}
