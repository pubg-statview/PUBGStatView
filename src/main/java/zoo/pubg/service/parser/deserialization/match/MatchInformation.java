package zoo.pubg.service.parser.deserialization.match;

import java.util.List;
import lombok.Getter;
import zoo.pubg.domain.Match;
import zoo.pubg.domain.PlayerMatchResult;
import zoo.pubg.service.parser.deserialization.match.data.Data;
import zoo.pubg.service.parser.deserialization.match.data.MatchAttributes;
import zoo.pubg.service.parser.deserialization.match.included.Asset;
import zoo.pubg.service.parser.deserialization.match.included.Included;
import zoo.pubg.service.parser.deserialization.match.included.Participant;

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
        included.stream()
                .filter(p -> (p instanceof Participant))
                .map(p -> p);
        return List.of();
    }

    private String getTelemetryUrl() {
        for (Included inc : included) {
            System.out.println(inc);
            if (inc instanceof Asset asset) {
                return asset.getTelemetryUrl();
            }
        }
        throw new IllegalArgumentException("asset 없음");
    }
}
