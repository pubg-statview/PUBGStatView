package zoo.pubg.service.parser.deserialization.player;

import java.util.List;

public record PlayerRelationships(Matches matches) {
    public List<String> getMatchIds() {
        return matches.getMatchIds();
    }
}
