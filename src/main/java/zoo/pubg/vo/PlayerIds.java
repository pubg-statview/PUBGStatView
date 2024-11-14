package zoo.pubg.vo;

import java.util.ArrayList;
import java.util.List;

public class PlayerIds {

    private final List<PlayerId> playerIds;

    public PlayerIds() {
        this.playerIds = new ArrayList<>();
    }

    public PlayerIds(List<PlayerId> playerIds) {
        this.playerIds = playerIds;
    }

    public static PlayerIds from(List<String> stringIds) {
        return new PlayerIds(
                stringIds.stream().map(PlayerId::new).toList()
        );
    }

    public void add(PlayerId playerId) {
        playerIds.add(playerId);
    }

    public String joinToString() {
        return String.join(",", playerIds.stream().map(PlayerId::getPlayerId).toList());
    }

    public boolean isEmpty() {
        return playerIds.isEmpty();
    }

    public int size() {
        return playerIds.size();
    }

    public void clear() {
        playerIds.clear();
    }
}
