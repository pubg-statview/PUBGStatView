package zoo.pubg.service.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "pubgBasicAPI", url = "https://api.pubg.com")
public interface PubgBasicApi {

    @GetMapping("/shards/{shards}/players")
    String fetchPlayerStatsByName(
            @PathVariable("shards") String shards,
            @RequestParam("filter[playerNames]") String playerName
    );

    @GetMapping("/shards/{shards}/players")
    String fetchPlayerStatsById(
            @PathVariable("shards") String shards,
            @RequestParam("filter[playerIds]") String playerIds
    );

    @GetMapping("/shards/{shards}/matches/{matchId}")
    String fetchPlayerMatch(
            @PathVariable("shards") String shards,
            @PathVariable("matchId") String matchId
    );

    @GetMapping("/shards/{shards}/seasons")
    String fetchSeasons(
            @PathVariable("shards") String shards
    );

    @GetMapping("/shards/{shards}/players/{playerId}/seasons/{seasonId}/ranked")
    String fetchRank(
            @PathVariable("shards") String shards,
            @PathVariable("playerId") String playerId,
            @PathVariable("seasonId") String seasonId
    );
}
