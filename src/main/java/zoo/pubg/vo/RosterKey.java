package zoo.pubg.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import zoo.pubg.domain.RosterMatchResult;

public record RosterKey(@JsonProperty("rank") Integer rank, @JsonProperty("teamId") Integer teamId) {

    public static RosterKey from(RosterMatchResult from) {
        return new RosterKey(
                from.getRank(), from.getTeamId()
        );
    }
}
