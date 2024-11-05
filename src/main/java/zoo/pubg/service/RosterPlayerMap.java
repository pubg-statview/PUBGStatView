package zoo.pubg.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import zoo.pubg.domain.PlayerMatchResult;
import zoo.pubg.domain.RosterMatchResult;
import zoo.pubg.dto.PlayerResultDto;
import zoo.pubg.vo.RosterKey;

@Getter
public class RosterPlayerMap {

    private final Map<RosterKey, List<PlayerResultDto>> map;

    public RosterPlayerMap() {
        map = new HashMap<>();
    }

    public void put(RosterMatchResult result, PlayerMatchResult value) {
        RosterKey key = RosterKey.from(result);
        PlayerResultDto playerResultDto = PlayerResultDto.from(value);
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(playerResultDto);
    }

    public List<PlayerResultDto> get(RosterMatchResult result) {
        RosterKey key = RosterKey.from(result);
        return new ArrayList<>(map.get(key));
    }
}
