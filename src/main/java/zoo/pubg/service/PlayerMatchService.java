package zoo.pubg.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.repository.MatchRepository;
import zoo.pubg.repository.PlayerMatchRepository;
import zoo.pubg.service.api.PubgBasicApi;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlayerMatchService {

    private final PubgBasicApi pubgBasicApi;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private PlayerMatchRepository playerMatchRepository;


    @Transactional
    public String fetchMatchHistory(String shards, String matchId) {
        String responseString = pubgBasicApi.fetchPlayerMatch(shards, matchId);
        return responseString;
    }

}
