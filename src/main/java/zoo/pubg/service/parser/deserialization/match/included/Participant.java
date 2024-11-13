package zoo.pubg.service.parser.deserialization.match.included;

import lombok.Getter;
import lombok.Setter;
import zoo.pubg.service.dto.ParticipantDto;
import zoo.pubg.service.parser.deserialization.match.included.participant.HistoryAttributes;
import zoo.pubg.service.parser.deserialization.match.included.participant.PlayerMatchStats;
import zoo.pubg.vo.PlayerMatchId;

@Getter
@Setter
public class Participant extends Included {

    private PlayerMatchId id;
    private HistoryAttributes attributes;

    public ParticipantDto toDto() {
        PlayerMatchStats stats = attributes.stats();
        return new ParticipantDto(
                id, stats.playerId(), stats.name(),
                stats.dbno(), stats.assists(), stats.kills(), stats.headshotKills(),
                stats.damageDealt(), stats.longestKill(), stats.winPlace()
        );
    }
}
