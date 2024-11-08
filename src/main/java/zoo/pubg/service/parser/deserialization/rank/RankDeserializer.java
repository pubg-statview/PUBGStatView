package zoo.pubg.service.parser.deserialization.rank;

import java.util.List;
import zoo.pubg.service.dto.RankedDetailsDto;

public record RankDeserializer(RankData data) {

    public List<RankedDetailsDto> getAllDetails() {
        return data.getAllDetails();
    }
}
