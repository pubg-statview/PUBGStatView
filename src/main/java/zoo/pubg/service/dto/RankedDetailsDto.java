package zoo.pubg.service.dto;

import zoo.pubg.constant.GameModeType;
import zoo.pubg.domain.rank.RankedDetails;

public record RankedDetailsDto(GameModeType type, RankedDetails details) {
}
