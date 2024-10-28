package zoo.pubg.service.parser.deserialization.player;

import zoo.pubg.vo.PlayerName;

public record PlayerAttributes(String shardId, String clanId, PlayerName name) {
}
