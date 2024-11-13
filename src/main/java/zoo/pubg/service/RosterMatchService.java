package zoo.pubg.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zoo.pubg.domain.Match;
import zoo.pubg.domain.RosterMatchResult;
import zoo.pubg.repository.RosterMatchRepository;
import zoo.pubg.service.dto.RosterDto;

@Service
public class RosterMatchService {

    @Autowired
    private RosterMatchRepository rosterMatchRepository;

    public PlayerRosterMap fetch(Match match, List<RosterDto> rosterDtos) {
        PlayerRosterMap map = new PlayerRosterMap();
        rosterDtos.forEach(rosterDto -> fetch(match, rosterDto, map));
        return map;
    }

    private void fetch(Match match, RosterDto dto, PlayerRosterMap map) {
        RosterMatchResult rosterMatchResult = new RosterMatchResult(
                dto.rosterId(), dto.rank(), dto.teamId(), match
        );
        dto.playerMatchIds().forEach(id -> map.put(id, rosterMatchResult));
        rosterMatchRepository.save(rosterMatchResult);
    }
}
