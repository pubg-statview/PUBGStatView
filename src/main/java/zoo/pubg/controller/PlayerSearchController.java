package zoo.pubg.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zoo.pubg.constant.Shards;
import zoo.pubg.domain.Player;
import zoo.pubg.dto.PlayerMatchIdsDto;
import zoo.pubg.exception.NotFoundException;
import zoo.pubg.exception.TooManyRequestsException;
import zoo.pubg.service.MatchService;
import zoo.pubg.service.PlayerService;
import zoo.pubg.vo.PlayerName;

@RestController
@RequestMapping("/api/search")
public class PlayerSearchController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private MatchService matchService;

    @GetMapping("/shards/{shards}/player/{playerName}")
    public ResponseEntity<Player> searchPlayer(
            @PathVariable Shards shards,
            @PathVariable PlayerName playerName) {
        Player player = playerService.searchPlayer(playerName);
        if (player == null) {
            try {
                return ResponseEntity.ok(fetchHelper(shards, playerName));
            } catch (JsonProcessingException | TooManyRequestsException | NotFoundException e) {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(player);
    }

    @GetMapping("/fetch/shards/{shards}/player/{playerName}")
    public ResponseEntity<Player> fetchPlayer(
            @PathVariable Shards shards,
            @PathVariable PlayerName playerName) {
        try {
            return ResponseEntity.ok(fetchHelper(shards, playerName));
        } catch (JsonProcessingException | TooManyRequestsException | NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private Player fetchHelper(Shards shards, PlayerName playerName) throws JsonProcessingException {
        PlayerMatchIdsDto playerMatchIdsDto = playerService.fetchPlayer(shards, playerName);
        matchService.fetchMatches(playerMatchIdsDto.matchIdsDto());
        return playerMatchIdsDto.player();
    }
}
