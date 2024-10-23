package zoo.pubg.service.dto.player;

import java.util.List;

public record PlayerRelationships(Matches matches) {
    public List<String> getMatchIds() {
        return matches.getMatchIds();
    }
}
