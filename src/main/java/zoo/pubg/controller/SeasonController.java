package zoo.pubg.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zoo.pubg.constant.Shards;
import zoo.pubg.exception.TooManyRequestsException;
import zoo.pubg.service.SeasonService;

@RequestMapping("/api/season")
@Controller
public class SeasonController {

    @Autowired
    private SeasonService seasonService;

    @GetMapping("/fetch")
    public ResponseEntity<String> fetchSeason(@RequestParam String shards) {
        Shards shardId = Shards.of(shards);
        try {
            seasonService.fetch(shardId);
            return ResponseEntity.ok("season fetch finish: " + shards);
        } catch (JsonProcessingException | TooManyRequestsException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
