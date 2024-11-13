package zoo.pubg.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.constant.Shards;
import zoo.pubg.domain.Player;
import zoo.pubg.dto.MatchIdsDto;
import zoo.pubg.dto.PlayerMatchIdsDto;
import zoo.pubg.repository.PlayerRepository;
import zoo.pubg.service.api.PubgBasicApi;
import zoo.pubg.service.parser.PlayerApiParser;
import zoo.pubg.service.parser.deserialization.player.PlayerData;
import zoo.pubg.service.parser.deserialization.player.PlayerDto;
import zoo.pubg.vo.PlayerIds;
import zoo.pubg.vo.PlayerName;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RequiredArgsConstructor
public class PlayerService {

    private final static PlayerApiParser parser = new PlayerApiParser();

    private final PubgBasicApi pubgBasicAPI;

    @Autowired
    private PlayerRepository playerRepository;

    @Transactional(readOnly = true)
    public Player searchPlayer(PlayerName name) {
        return playerRepository.findByName(name);
    }

    public PlayerMatchIdsDto fetchPlayer(Shards shards, PlayerName name) throws JsonProcessingException {
        String responseString = pubgBasicAPI.fetchPlayerStatsByName(shards.getShardName(), name.getName());
        PlayerDto playerDto = parser.parse(responseString);
        PlayerData data = playerDto.getFirstPlayer();
        Player player = data.toEntity();
        playerRepository.save(player);
        return new PlayerMatchIdsDto(
                player, new MatchIdsDto(shards, data.getMatchIds())
        );
    }

    public void fetchPlayersByIds(Shards shards, PlayerIds ids) throws JsonProcessingException {
        if (ids.isEmpty()) {
            return;
        }
        if (ids.size() > 10) {
            throw new IllegalArgumentException("10개 이하여야함 (API 제한)");
        }
        String joinId = ids.joinToString();
        String response = pubgBasicAPI.fetchPlayerStatsById(shards.getShardName(), joinId);
        PlayerDto playerDto = parser.parse(response);
        for (PlayerData data : playerDto.data()) {
            Player player = data.toEntity();
            playerRepository.save(player);
        }
    }
}
