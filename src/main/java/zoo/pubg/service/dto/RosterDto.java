package zoo.pubg.service.dto;

import java.util.List;

public record RosterDto(
        String rosterId, int rank, int teamId, List<String> playerMatchId
) {
}
