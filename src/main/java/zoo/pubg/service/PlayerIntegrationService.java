package zoo.pubg.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zoo.pubg.constant.Shards;
import zoo.pubg.domain.Player;
import zoo.pubg.domain.list.Players;
import zoo.pubg.dto.PlayerMatchIdsDto;
import zoo.pubg.vo.PlayerName;
import zoo.pubg.vo.list.PlayerNames;

@Service
public class PlayerIntegrationService {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private RankService rankService;

    @Autowired
    private MatchService matchService;

    public Player fetchPlayer(Shards shards, PlayerName playerName) throws JsonProcessingException {
        PlayerMatchIdsDto playerMatchIdsDto = playerService.fetchPlayer(shards, playerName);
        fetchPlayerInformation(shards, playerMatchIdsDto);
        return playerMatchIdsDto.player();
    }

    public Players fetchPlgayers(Shards shards, PlayerNames playerNames) throws JsonProcessingException {
        List<PlayerMatchIdsDto> playerMatchIdsDtos = playerService.fetchPlayersByNames(shards, playerNames);
        for (PlayerMatchIdsDto playerMatchIdsDto : playerMatchIdsDtos) {
            fetchPlayerInformation(shards, playerMatchIdsDto);
        }
        return new Players(playerMatchIdsDtos.stream().map(PlayerMatchIdsDto::player).toList());
    }

    private void fetchPlayerInformation(Shards shards, PlayerMatchIdsDto playerMatchIdsDto)
            throws JsonProcessingException {
        Player player = playerMatchIdsDto.player();
        rankService.fetchCurrentSeasonRank(shards, player);
        matchService.fetchMatches(playerMatchIdsDto.matchIdsDto());
    }
}
