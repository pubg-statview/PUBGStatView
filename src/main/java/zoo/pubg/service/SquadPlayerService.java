package zoo.pubg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.domain.Player;
import zoo.pubg.domain.Squad;
import zoo.pubg.domain.SquadPlayer;
import zoo.pubg.domain.list.Players;
import zoo.pubg.repository.SquadPlayerRepository;
import zoo.pubg.repository.SquadRepository;
import zoo.pubg.vo.SquadId;

@Service
@Transactional
public class SquadPlayerService {

    @Autowired
    private SquadRepository squadRepository;

    @Autowired
    private SquadPlayerRepository squadPlayerRepository;

    public void fetchSquadPlayers(SquadId squadId, Players players) {
        Squad found = squadRepository.find(squadId);
        if (found != null) {
            squadRepository.update(found);
            return;
        }

        Squad squad = new Squad(squadId);
        for (Player player : players) {
            SquadPlayer squadPlayer = new SquadPlayer(squad, player);
            squadPlayerRepository.save(squadPlayer);
        }
    }
}
