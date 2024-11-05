package zoo.pubg.dto;

import lombok.Getter;
import zoo.pubg.domain.PlayerMatchResult;
import zoo.pubg.service.RosterPlayerMap;
import zoo.pubg.vo.RosterId;

@Getter
public record PlayerMatchResultDto(MatchDto matchDto, PlayerResultDto playerResultDto, RosterId rosterId,
                                   RosterPlayerMap rosterPlayerMap) {

    public static PlayerMatchResultDto from(
            PlayerMatchResult matchResult, RosterPlayerMap rosterPlayerMap
    ) {
        return new PlayerMatchResultDto(
                MatchDto.from(matchResult.getMatch()), PlayerResultDto.from(matchResult),
                matchResult.getRosterId(), rosterPlayerMap
        );
    }
}
