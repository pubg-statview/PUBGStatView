package zoo.pubg.constant;

import zoo.pubg.vo.PlayerId;

public enum PlayerType {
    PLAYER, BOT, ETC;

    public static PlayerType from(PlayerId playerId) {
        String id = playerId.getPlayerId();
        if (id.matches("^account\\.[a-f0-9]{32}$")) {
            return PLAYER;
        }
        if (id.matches("^ai\\.[a-f0-9]{1,5}")) {
            return BOT;
        }
        return ETC;
    }
}
