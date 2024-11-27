package zoo.pubg.dto;

import zoo.pubg.domain.Squad;
import zoo.pubg.domain.list.Players;

public record SquadPlayersDto(Squad squad, Players players) {
}
