package zoo.pubg.vo.list;

import java.util.List;
import zoo.pubg.vo.PlayerId;

public class PlayerIds extends ValueList<PlayerId> {

    public PlayerIds() {
        super();
    }

    public PlayerIds(List<PlayerId> playerIds) {
        super(playerIds);
    }

    public static PlayerIds from(List<String> stringIds) {
        return new PlayerIds(
                stringIds.stream().map(PlayerId::new).toList()
        );
    }

    public String joinToString() {
        return String.join(",", list.stream().map(PlayerId::getPlayerId).toList());
    }
}
