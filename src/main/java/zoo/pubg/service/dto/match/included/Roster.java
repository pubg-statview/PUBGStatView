package zoo.pubg.service.dto.match.included;

import lombok.Getter;
import lombok.Setter;
import zoo.pubg.service.dto.match.included.roster.RosterAttributes;
import zoo.pubg.service.dto.match.included.roster.RosterRelationships;

@Getter
@Setter
public class Roster extends Included {

    private String id;
    private RosterAttributes attributes;
    private RosterRelationships relationships;
}
