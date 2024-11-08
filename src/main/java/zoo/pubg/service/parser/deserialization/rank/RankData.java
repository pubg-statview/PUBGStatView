package zoo.pubg.service.parser.deserialization.rank;

import java.util.List;
import zoo.pubg.service.dto.RankedDetailsDto;

public record RankData(RankAttributes attributes) {

    public List<RankedDetailsDto> getAllDetails() {
        return attributes.getAllDetails();
    }
}
