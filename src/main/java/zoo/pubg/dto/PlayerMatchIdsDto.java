package zoo.pubg.dto;

import zoo.pubg.domain.Player;
import zoo.pubg.service.parser.deserialization.player.PlayerData;
import zoo.pubg.vo.PlayerName;

public record PlayerMatchIdsDto(Player player, MatchIdsDto matchIdsDto) {

    public static PlayerMatchIdsDto from(PlayerData data) {
        return new PlayerMatchIdsDto(
                data.toEntity(), MatchIdsDto.from(data)
        );
    }

    public PlayerName getPlayerName() {
        return player.getName();
    }
}
