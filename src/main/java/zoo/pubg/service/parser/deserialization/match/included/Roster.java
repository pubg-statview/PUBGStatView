package zoo.pubg.service.parser.deserialization.match.included;

import lombok.Getter;
import lombok.Setter;
import zoo.pubg.service.dto.RosterDto;
import zoo.pubg.service.parser.deserialization.match.included.roster.RosterAttributes;
import zoo.pubg.service.parser.deserialization.match.included.roster.RosterRelationships;
import zoo.pubg.service.parser.deserialization.match.included.roster.attributes.RosterMatchStats;

@Getter
@Setter
public class Roster extends Included {

    private String id;
    private RosterAttributes attributes;
    private RosterRelationships relationships;

    public RosterDto toDto() {
        RosterMatchStats stats = attributes.stats();
        return new RosterDto(
                id, stats.rank(), stats.teamId(), relationships.getParticipantIds()
        );
    }
}
