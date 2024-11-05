package zoo.pubg.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import zoo.pubg.domain.RosterMatchResult;

@EqualsAndHashCode
@RequiredArgsConstructor
@Getter
public class RosterKey {

    @JsonProperty("rank")
    private final Integer rank;

    @JsonProperty("teamId")
    private final Integer teamId;

    public static RosterKey from(RosterMatchResult from) {
        return new RosterKey(
                from.getRank(), from.getTeamId()
        );
    }
}
