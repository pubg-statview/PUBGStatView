package zoo.pubg.dto;

import java.util.List;
import zoo.pubg.constant.Shards;
import zoo.pubg.vo.MatchId;

public record MatchIdsDto(Shards shards, List<MatchId> matchIds) {

}
