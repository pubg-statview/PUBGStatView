package zoo.pubg.service.parser.deserialization.match.included;

import lombok.Getter;
import lombok.Setter;
import zoo.pubg.service.dto.ParticipantDto;
import zoo.pubg.service.parser.deserialization.match.included.participant.HistoryAttributes;
import zoo.pubg.service.parser.deserialization.match.included.participant.PlayerMatchStats;

@Getter
@Setter
public class Participant extends Included {

    private String id;
    private HistoryAttributes attributes;

    public ParticipantDto toDto() {
        PlayerMatchStats stats = attributes.stats();
        return new ParticipantDto(
                id, stats.playerId(),
                stats.dbno(), stats.assists(), stats.kills(), stats.headshotKills(),
                stats.damageDealt(), stats.longestKill()
        );
    }
}
