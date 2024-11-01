package zoo.pubg.service.parser.deserialization.player;

import java.util.List;
import zoo.pubg.vo.MatchId;

public record Matches(List<MatchData> data) {
    public List<MatchId> getMatchIds() {
        return data.stream().map(MatchData::id).toList();
    }
}
