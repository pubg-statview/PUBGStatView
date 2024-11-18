package zoo.pubg.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zoo.pubg.constant.Shards;
import zoo.pubg.domain.Squad;
import zoo.pubg.repository.SquadPlayerRepository;
import zoo.pubg.repository.SquadRepository;
import zoo.pubg.vo.list.PlayerNames;

@Service
public class SquadPlayerService {

    @Autowired
    private SquadRepository squadRepository;

    @Autowired
    private SquadPlayerRepository squadPlayerRepository;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private MatchService matchService;

    public Squad fetchSquad(Shards shards, PlayerNames playerNames) throws JsonProcessingException {
        playerService.fetchPlayersByNames(shards, playerNames);
        return null;
    }
}
