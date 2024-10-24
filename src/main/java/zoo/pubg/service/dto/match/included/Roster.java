package zoo.pubg.service.dto.match.included;

import zoo.pubg.service.dto.match.included.roster.RosterAttributes;
import zoo.pubg.service.dto.match.included.roster.RosterRelationships;

public class Roster extends Included {

    private String id;
    private RosterAttributes attributes;
    private RosterRelationships relationships;
}
