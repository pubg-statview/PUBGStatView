package zoo.pubg.service.parser.deserialization.player;

import java.util.List;

public record PlayerDto(List<PlayerData> data) {

    public PlayerData getFirstPlayer() {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("PlayerDto");
        }
        return data.get(0);
    }
}
