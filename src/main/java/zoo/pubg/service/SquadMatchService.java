package zoo.pubg.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.domain.Match;
import zoo.pubg.domain.PlayerMatchResult;
import zoo.pubg.domain.Squad;
import zoo.pubg.domain.SquadMatchResult;
import zoo.pubg.domain.list.Players;
import zoo.pubg.repository.PlayerMatchRepository;
import zoo.pubg.repository.SquadMatchRepository;
import zoo.pubg.vo.SquadMatchStatisticalData;

@Service
@Transactional
public class SquadMatchService {

    @Autowired
    private PlayerMatchRepository playerMatchRepository;

    @Autowired
    private SquadMatchRepository squadMatchRepository;

    public void fetchSquadMatchResult(Squad squad, Players players) {
        Map<Match, List<PlayerMatchResult>> map = new HashMap<>();
        List<PlayerMatchResult> results = playerMatchRepository.findByPlayersInSameRoster(players);
        for (PlayerMatchResult playerMatchResult : results) {
            Match match = playerMatchResult.getMatch();
            if (!map.containsKey(match)) {
                map.put(match, new ArrayList<>());
            }
            map.get(match).add(playerMatchResult);
        }
        for (Match match : map.keySet()) {
            List<PlayerMatchResult> playerMatchResults = map.get(match);
            SquadMatchStatisticalData data = SquadMatchStatisticalData.from(playerMatchResults);
            SquadMatchResult result = new SquadMatchResult(squad, match, data);
            squadMatchRepository.save(result);
        }
    }
}
