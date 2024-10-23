package zoo.pubg.service.dto.player;

import java.util.List;

public record Matches(List<MatchData> data) {
    public List<String> getMatchIds() {
        return data.stream().map(MatchData::id).toList();
    }
}
