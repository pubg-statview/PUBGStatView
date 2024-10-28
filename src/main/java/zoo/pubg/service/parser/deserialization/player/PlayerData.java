package zoo.pubg.service.parser.deserialization.player;

import java.time.LocalDateTime;
import java.util.List;
import zoo.pubg.domain.Player;
import zoo.pubg.vo.MatchId;
import zoo.pubg.vo.PlayerId;

public record PlayerData(
        PlayerId id, PlayerAttributes attributes, PlayerRelationships relationships
) {
    public Player toEntity() {
        return new Player(
                id, attributes.name(), attributes.shardId(), attributes.clanId(), LocalDateTime.now()
        );
    }

    public List<MatchId> getMatchIds() {
        return relationships.getMatchIds();
    }
}
