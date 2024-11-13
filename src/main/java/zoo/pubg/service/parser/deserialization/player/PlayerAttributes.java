package zoo.pubg.service.parser.deserialization.player;

import zoo.pubg.constant.Shards;
import zoo.pubg.vo.PlayerName;

public record PlayerAttributes(Shards shardId, String clanId, PlayerName name) {
}
