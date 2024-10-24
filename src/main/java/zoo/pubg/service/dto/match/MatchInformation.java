package zoo.pubg.service.dto.match;

import java.util.List;
import lombok.Getter;
import zoo.pubg.domain.Match;
import zoo.pubg.domain.PlayerMatchResult;
import zoo.pubg.service.dto.match.data.Data;
import zoo.pubg.service.dto.match.data.MatchAttributes;
import zoo.pubg.service.dto.match.included.Asset;
import zoo.pubg.service.dto.match.included.Included;

@Getter
public class MatchInformation {

    private Data data;
    private List<Included> included;

    public Match getMatchEntity() {
        MatchAttributes attributes = data.getAttributes();
        return new Match(
                data.getId(),
                attributes.mapName(),
                attributes.gameMode(),
                attributes.matchType(),
                attributes.duration(),
                getTelemetryUrl(),
                attributes.createdAt().toLocalDateTime()
        );
    }

    public List<PlayerMatchResult> getPlayerMatchResult() {
        return null;
    }

    private String getTelemetryUrl() {
        for (Included inc : included) {
            if (inc instanceof Asset asset) {
                return asset.getTelemetryUrl();
            }
        }
        throw new IllegalArgumentException("asset 없음");
    }
}
