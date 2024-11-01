package zoo.pubg.service.parser.deserialization.match.included.roster.relationships;

import java.util.List;
import zoo.pubg.service.parser.deserialization.match.included.roster.relationships.participants.RosterParticipantData;
import zoo.pubg.vo.PlayerMatchId;

public record RosterParticipants(List<RosterParticipantData> data) {

    public List<PlayerMatchId> getIds() {
        return data.stream()
                .map(RosterParticipantData::id)
                .toList();
    }
}
