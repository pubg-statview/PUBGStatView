package zoo.pubg.service.dto.match.included.roster.relationships;

import java.util.List;
import zoo.pubg.service.dto.match.included.roster.relationships.participants.RosterParticipantData;

public record RosterParticipants(List<RosterParticipantData> data) {

}
