package zoo.pubg.service.parser.deserialization.rank;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RankedGameModeStats(
        RankDataDetails squad,
        @JsonProperty("squad-fpp") RankDataDetails squadFpp,
        RankDataDetails duo,
        @JsonProperty("duo-fpp") RankDataDetails duoFpp,
        RankDataDetails solo,
        @JsonProperty("solo-fpp") RankDataDetails soloFpp
) {

}
