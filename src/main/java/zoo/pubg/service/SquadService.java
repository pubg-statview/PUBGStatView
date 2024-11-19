package zoo.pubg.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.constant.Shards;
import zoo.pubg.domain.Player;
import zoo.pubg.domain.Squad;
import zoo.pubg.domain.list.Players;
import zoo.pubg.repository.PlayerRepository;
import zoo.pubg.repository.SquadRepository;
import zoo.pubg.vo.PlayerName;
import zoo.pubg.vo.SquadId;
import zoo.pubg.vo.list.PlayerNames;

@Service
public class SquadService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private SquadRepository squadRepository;

    @Autowired
    private PlayerIntegrationService playerIntegrationService;

    @Transactional(readOnly = true)
    public Squad searchSquad(PlayerNames playerNames) {
        Players players = new Players();
        for (PlayerName playerName : playerNames.getList()) {
            Player player = playerRepository.findByName(playerName);
            if (player == null) {
                return null;
            }
            players.add(player);
        }

        return squadRepository.find(SquadId.from(players.getPlayerIds()));
    }

    public void fetchSquad(Shards shards, PlayerNames playerNames) throws JsonProcessingException {
        Players players = playerIntegrationService.fetchPlayers(shards, playerNames);
        String s = playerNames.joinToString();
    }
}
