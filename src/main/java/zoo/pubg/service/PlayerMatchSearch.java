package zoo.pubg.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.domain.Match;
import zoo.pubg.domain.Player;
import zoo.pubg.domain.PlayerMatchResult;
import zoo.pubg.dto.PlayerMatchResultDto;
import zoo.pubg.repository.PlayerMatchRepository;
import zoo.pubg.repository.PlayerRepository;

@Service
@Transactional(readOnly = true)
public class PlayerMatchSearch {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerMatchRepository playerMatchRepository;


    public Page<PlayerMatchResultDto> findAllWithPagination(Player player, int page, int size) {
        Page<PlayerMatchResult> results = playerMatchRepository.findAllWithPagination(player, page, size);
        return results.map(result -> PlayerMatchResultDto.from(result, buildRosterPlayerMap(result.getMatch())));
    }

    private RosterPlayerMap buildRosterPlayerMap(Match match) {
        List<PlayerMatchResult> allParticipants = playerMatchRepository.findAllParticipants(match);
        RosterPlayerMap rosterPlayerMap = new RosterPlayerMap();
        for (PlayerMatchResult allParticipant : allParticipants) {
            rosterPlayerMap.put(allParticipant.getRosterMatchResult(), allParticipant);
        }
        return rosterPlayerMap;
    }
}
