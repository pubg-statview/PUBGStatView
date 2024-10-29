package zoo.pubg.service.dto;

import java.util.List;
import zoo.pubg.vo.PlayerMatchId;
import zoo.pubg.vo.RosterId;

public record RosterDto(
        RosterId rosterId, int rank, int teamId, List<PlayerMatchId> playerMatchIds
) {
}
