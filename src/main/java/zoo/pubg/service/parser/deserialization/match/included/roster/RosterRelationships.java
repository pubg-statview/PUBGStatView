package zoo.pubg.service.parser.deserialization.match.included.roster;

import java.util.List;
import zoo.pubg.service.parser.deserialization.match.included.roster.relationships.RosterParticipants;

public record RosterRelationships(RosterParticipants participants) {

    public List<String> getParticipantIds() {
        return participants.getIds();
    }
}
