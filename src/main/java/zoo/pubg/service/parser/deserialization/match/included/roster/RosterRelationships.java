package zoo.pubg.service.parser.deserialization.match.included.roster;

import java.util.List;
import zoo.pubg.service.parser.deserialization.match.included.roster.relationships.RosterParticipants;
import zoo.pubg.vo.PlayerMatchId;

public record RosterRelationships(RosterParticipants participants) {

    public List<PlayerMatchId> getParticipantIds() {
        return participants.getIds();
    }
}
