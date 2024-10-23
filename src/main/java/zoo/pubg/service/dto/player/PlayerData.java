package zoo.pubg.service.dto.player;

import java.time.LocalDateTime;
import java.util.List;
import zoo.pubg.domain.Player;

public record PlayerData(
        String id, PlayerAttributes attributes, PlayerRelationships relationships
) {
    public Player toEntity() {
        return new Player(
                id, attributes.name(), attributes.shardId(), attributes.clanId(), LocalDateTime.now()
        );
    }

    public List<String> getMatchIds() {
        return relationships.getMatchIds();
    }
}
