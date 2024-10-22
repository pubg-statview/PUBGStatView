package zoo.pubg.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.domain.Player;
import zoo.pubg.repository.PlayerRepository;
import zoo.pubg.service.api.PubgBasicApi;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlayerService {

    private final PubgBasicApi pubgBasicAPI;

    @Autowired
    private PlayerRepository playerRepository;

    public Player searchPlayer(String name) {
        return playerRepository.find(name);
    }

    @Transactional
    public String fetchPlayerStats(String shards, String name) {
        String responseString = pubgBasicAPI.fetchPlayerStats(shards, name);
        return responseString;
    }
}
