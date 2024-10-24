package zoo.pubg.service.dto.match.included;

import lombok.Getter;
import lombok.Setter;
import zoo.pubg.service.dto.match.included.participant.HistoryAttributes;

@Getter
@Setter
public class Participant extends Included {

    private String id;
    private HistoryAttributes attributes;
}
