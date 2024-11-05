package zoo.pubg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.domain.Match;
import zoo.pubg.repository.MatchRepository;
import zoo.pubg.vo.MatchId;

@Service
@Transactional(readOnly = true)
public class MatchSearch {

    @Autowired
    private MatchRepository matchRepository;

    public Match searchMatch(MatchId matchId) {
        return matchRepository.find(matchId);
    }
}
