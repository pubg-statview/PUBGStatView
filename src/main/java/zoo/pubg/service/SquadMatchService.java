package zoo.pubg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.domain.list.Players;
import zoo.pubg.repository.PlayerMatchRepository;

@Service
@Transactional
public class SquadMatchService {

    @Autowired
    private PlayerMatchRepository playerMatchRepository;

    public void fetchSquadMatchResult(Players players) {

    }
}
