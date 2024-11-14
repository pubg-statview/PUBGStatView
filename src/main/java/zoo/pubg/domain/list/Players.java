package zoo.pubg.domain.list;

import java.util.ArrayList;
import java.util.List;
import zoo.pubg.domain.Player;
import zoo.pubg.vo.PlayerIds;
import zoo.pubg.vo.PlayerNames;

public class Players {

    private final List<Player> players;

    public Players() {
        this.players = new ArrayList<>();
    }

    public Players(List<Player> players) {
        this.players = players;
    }

    public void add(Player player) {
        players.add(player);
    }

    public PlayerNames getPlayerNames() {
        return new PlayerNames(
                players.stream()
                        .map(Player::getName)
                        .toList());
    }

    public PlayerIds getPlayerIds() {
        return new PlayerIds(
                players.stream()
                        .map(Player::getPlayerId)
                        .toList()
        );
    }
}
