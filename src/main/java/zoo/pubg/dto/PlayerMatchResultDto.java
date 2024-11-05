package zoo.pubg.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import zoo.pubg.domain.PlayerMatchResult;
import zoo.pubg.service.RosterPlayerMap;
import zoo.pubg.vo.RosterId;

@RequiredArgsConstructor
@Getter
public class PlayerMatchResultDto {

    private final MatchDto matchDto;
    private final PlayerResultDto playerResultDto;
    private final RosterId rosterId;
    private final RosterPlayerMap rosterPlayerMap;

    public static PlayerMatchResultDto from(
            PlayerMatchResult matchResult, RosterPlayerMap rosterPlayerMap
    ) {
        return new PlayerMatchResultDto(
                MatchDto.from(matchResult.getMatch()), PlayerResultDto.from(matchResult),
                matchResult.getRosterId(), rosterPlayerMap
        );
    }
}
