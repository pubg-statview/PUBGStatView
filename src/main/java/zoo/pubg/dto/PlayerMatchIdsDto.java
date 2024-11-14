package zoo.pubg.dto;

import zoo.pubg.domain.Player;
import zoo.pubg.service.parser.deserialization.player.PlayerData;

public record PlayerMatchIdsDto(Player player, MatchIdsDto matchIdsDto) {

    public static PlayerMatchIdsDto from(PlayerData data) {
        return new PlayerMatchIdsDto(
                data.toEntity(), MatchIdsDto.from(data)
        );
    }
}
