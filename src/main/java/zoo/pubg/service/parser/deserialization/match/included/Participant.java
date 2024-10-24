package zoo.pubg.service.parser.deserialization.match.included;

import lombok.Getter;
import lombok.Setter;
import zoo.pubg.domain.PlayerMatchResult;
import zoo.pubg.service.parser.deserialization.match.included.participant.HistoryAttributes;

@Getter
@Setter
public class Participant extends Included {

    private String id;
    private HistoryAttributes attributes;

    public PlayerMatchResult toEntity() {
        return new PlayerMatchResult();
    }
}
