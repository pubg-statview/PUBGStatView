package zoo.pubg.service.parser.deserialization.rank;

import java.util.List;
import zoo.pubg.service.dto.RankedDetailsDto;

public record RankAttributes(RankedGameModeStats rankedGameModeStats) {

    public List<RankedDetailsDto> getAllDetails() {
        return rankedGameModeStats.getAllDetails();
    }
}
