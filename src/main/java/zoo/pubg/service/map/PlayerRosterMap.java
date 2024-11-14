package zoo.pubg.service.map;

import java.util.HashMap;
import java.util.Map;
import zoo.pubg.domain.RosterMatchResult;
import zoo.pubg.vo.PlayerMatchId;

public class PlayerRosterMap {

    private final Map<PlayerMatchId, RosterMatchResult> map;

    public PlayerRosterMap() {
        this.map = new HashMap<>();
    }

    public void put(PlayerMatchId key, RosterMatchResult value) {
        map.put(key, value);
    }

    public RosterMatchResult get(PlayerMatchId key) {
        return map.get(key);
    }

    public int size() {
        return map.size();
    }

    public void remove(PlayerMatchId key) {
        map.remove(key);
    }
}
