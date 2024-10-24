package zoo.pubg.service.dto.match;

import java.util.List;
import lombok.Getter;
import zoo.pubg.service.dto.match.data.Data;
import zoo.pubg.service.dto.match.included.Includes;

@Getter
public class MatchInformation {

    private Data data;
    private List<Includes> included;

}
