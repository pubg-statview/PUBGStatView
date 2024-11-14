package zoo.pubg.dto;

import java.util.List;
import zoo.pubg.constant.Shards;
import zoo.pubg.service.parser.deserialization.player.PlayerData;
import zoo.pubg.vo.MatchId;

public record MatchIdsDto(Shards shards, List<MatchId> matchIds) {

    public static MatchIdsDto from(PlayerData data) {
        return new MatchIdsDto(
                data.attributes().shardId(),
                data.getMatchIds()
        );
    }
}
