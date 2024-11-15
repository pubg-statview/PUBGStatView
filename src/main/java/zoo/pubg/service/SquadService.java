package zoo.pubg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.domain.Player;
import zoo.pubg.domain.Squad;
import zoo.pubg.domain.list.Players;
import zoo.pubg.repository.PlayerRepository;
import zoo.pubg.repository.SquadRepository;
import zoo.pubg.vo.PlayerName;
import zoo.pubg.vo.PlayerNames;
import zoo.pubg.vo.SquadId;

@Service
@Transactional
public class SquadService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private SquadRepository squadRepository;

    public Squad searchSquad(PlayerNames playerNames) {
        Players players = new Players();
        for (PlayerName playerName : playerNames.playerNames()) {
            Player player = playerRepository.findByName(playerName);
            if (player == null) {
                return null;
            }
            players.add(player);
        }

        return squadRepository.find(SquadId.from(players.getPlayerIds()));
    }
}
