package zoo.pubg.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.domain.Player;
import zoo.pubg.repository.PlayerRepository;
import zoo.pubg.service.api.PubgBasicApi;
import zoo.pubg.service.dto.player.PlayerData;
import zoo.pubg.service.dto.player.PlayerDto;
import zoo.pubg.service.parser.PlayerApiParser;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlayerService {

    private final static PlayerApiParser parser = new PlayerApiParser();

    private final PubgBasicApi pubgBasicAPI;

    @Autowired
    private PlayerRepository playerRepository;

    public Player searchPlayer(String name) {
        return playerRepository.find(name);
    }

    @Transactional
    public String fetchPlayerStats(String shards, String name) throws JsonProcessingException {
        String responseString = pubgBasicAPI.fetchPlayerStats(shards, name);
        PlayerDto playerDto = parser.parse(responseString);
        for (PlayerData data : playerDto.data()) {
            Player player = data.toEntity();
            playerRepository.save(player);
        }
        return responseString;
    }
}
