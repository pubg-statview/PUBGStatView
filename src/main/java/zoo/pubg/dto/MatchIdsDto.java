package zoo.pubg.dto;

import java.util.List;
import lombok.Getter;
import zoo.pubg.constant.Shards;
import zoo.pubg.vo.MatchId;

@Getter
public class MatchIdsDto {

    private final Shards shards;
    private final List<MatchId> matchIds;

    public MatchIdsDto(Shards shards, List<MatchId> matchIds) {
        this.shards = shards;
        this.matchIds = matchIds;
    }

    public static MatchIdsDto from(Shards shards, List<String> id) {
        return new MatchIdsDto(
                shards, id.stream().map(MatchId::new).toList()
        );
    }
}
