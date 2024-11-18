package zoo.pubg.domain.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import zoo.pubg.domain.Player;
import zoo.pubg.vo.list.PlayerIds;
import zoo.pubg.vo.list.PlayerNames;

public class Players implements Iterable<Player> {

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

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}
