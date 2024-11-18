package zoo.pubg.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zoo.pubg.constant.Shards;
import zoo.pubg.dto.PlayerMatchIdsDto;
import zoo.pubg.service.api.PubgBasicApi;
import zoo.pubg.service.parser.PlayerApiParser;
import zoo.pubg.service.parser.deserialization.player.PlayerDto;
import zoo.pubg.vo.PlayerName;
import zoo.pubg.vo.list.PlayerIds;
import zoo.pubg.vo.list.PlayerNames;

@Service
@RequiredArgsConstructor
public class PlayerApiService {

    private final static PlayerApiParser parser = new PlayerApiParser();

    private final PubgBasicApi pubgBasicAPI;

    public PlayerMatchIdsDto fetchPlayer(Shards shards, PlayerName name) throws JsonProcessingException {
        String responseString = pubgBasicAPI.fetchPlayerStatsByName(shards.getShardName(), name.getName());
        PlayerDto playerDto = parser.parse(responseString);
        List<PlayerMatchIdsDto> playerMatchIdsDtos = toDtoList(playerDto);
        return playerMatchIdsDtos.get(0);
    }


    public List<PlayerMatchIdsDto> fetchPlayersByIds(Shards shards, PlayerIds ids) throws JsonProcessingException {
        String response = pubgBasicAPI.fetchPlayerStatsById(shards.getShardName(), ids.joinToString());
        PlayerDto playerDto = parser.parse(response);
        return toDtoList(playerDto);
    }

    public List<PlayerMatchIdsDto> fetchPlayersByNames(Shards shards, PlayerNames names)
            throws JsonProcessingException {
        String response = pubgBasicAPI.fetchPlayerStatsByName(shards.getShardName(), names.joinToString());
        PlayerDto playerDto = parser.parse(response);
        return toDtoList(playerDto);
    }

    private List<PlayerMatchIdsDto> toDtoList(PlayerDto playerDto) {
        if (playerDto == null || playerDto.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return playerDto.data().stream()
                .map(PlayerMatchIdsDto::from)
                .toList();
    }
}
