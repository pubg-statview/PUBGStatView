package zoo.pubg.service.dto.match;

import java.util.List;
import zoo.pubg.service.dto.match.data.Data;
import zoo.pubg.service.dto.match.included.Includes;

public record MatchDto(Data data, List<Includes> included) {
}
