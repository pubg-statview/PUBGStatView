package zoo.pubg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zoo.pubg.domain.Player;
import zoo.pubg.dto.PlayerMatchResultDto;
import zoo.pubg.service.PlayerMatchSearch;
import zoo.pubg.service.PlayerService;
import zoo.pubg.vo.PlayerName;

@RestController
@RequestMapping("/api/search-match")
public class MatchSearchController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerMatchSearch playerMatchSearch;

    @GetMapping("/{playerName}")
    public ResponseEntity<Page<PlayerMatchResultDto>> getPlayerMatches(
            @PathVariable PlayerName playerName,
            @RequestParam int page,
            @RequestParam int size) {
        Player player = playerService.searchPlayer(playerName);
        if (player == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(
                playerMatchSearch.findAllWithPagination(player, page, size)
        );
    }
}
