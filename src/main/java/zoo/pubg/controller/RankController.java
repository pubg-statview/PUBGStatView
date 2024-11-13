package zoo.pubg.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zoo.pubg.constant.Shards;
import zoo.pubg.domain.Player;
import zoo.pubg.domain.rank.Season;
import zoo.pubg.service.PlayerService;
import zoo.pubg.service.RankService;
import zoo.pubg.service.SeasonService;
import zoo.pubg.vo.PlayerName;

@RequestMapping("/api/rank")
@Controller
public class RankController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private SeasonService seasonService;

    @Autowired
    private RankService rankService;

    @GetMapping("/current-season-fetch")
    public ResponseEntity<String> fetchCurrentSeasonRanks(
            @RequestParam String shards,
            @RequestParam PlayerName playerName) {
        Shards shardId = Shards.of(shards);
        Player player = playerService.searchPlayer(playerName);
        Season season = seasonService.getCurrentSeason(shardId);

        try {
            rankService.fetch(shardId, player, season);
        } catch (JsonProcessingException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(playerName.getName() + " rank fetch finish");
    }
}
