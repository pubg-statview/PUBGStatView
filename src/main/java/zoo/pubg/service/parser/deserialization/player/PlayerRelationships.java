package zoo.pubg.service.parser.deserialization.player;

import java.util.List;
import zoo.pubg.vo.MatchId;

public record PlayerRelationships(Matches matches) {
    public List<MatchId> getMatchIds() {
        return matches.getMatchIds();
    }
}
