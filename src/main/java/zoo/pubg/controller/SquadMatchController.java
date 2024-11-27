package zoo.pubg.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zoo.pubg.constant.Shards;
import zoo.pubg.domain.Squad;
import zoo.pubg.domain.SquadMatchResult;
import zoo.pubg.dto.SquadPlayersDto;
import zoo.pubg.service.PlayerService;
import zoo.pubg.service.SquadMatchService;
import zoo.pubg.service.SquadService;
import zoo.pubg.vo.list.PlayerNames;

@Slf4j
@RestController
@RequestMapping("/api/squad")
public class SquadMatchController {

    @Autowired
    private SquadService squadService;

    @Autowired
    private SquadMatchService squadMatchService;

    @Autowired
    private PlayerService playerService;

    @GetMapping("/{playerNames}")
    public ResponseEntity<Page<SquadMatchResult>> getSquadMatches(
            @PathVariable String playerNames,
            @RequestParam int page,
            @RequestParam int size
    ) {
        try {
            List<String> playerNameList = Arrays.stream(playerNames.split(",")).toList();
            PlayerNames names = PlayerNames.from(playerNameList);
            Squad squad = squadService.searchSquad(names);
            if (squad == null) {
                return ResponseEntity.notFound().build();
            }
            Page<SquadMatchResult> allWithPagination = squadMatchService.findAllWithPagination(squad, page, size);
            return ResponseEntity.ok(allWithPagination);
        } catch (Exception e) {
            return ResponseEntity.status(123).build();
        }
    }

    @GetMapping("/fetch/shards/{shards}/players/{playerNames}")
    public ResponseEntity<String> fetchSquadMatches(
            @PathVariable String shards,
            @PathVariable String playerNames
    ) throws JsonProcessingException {
        try {
            List<String> playerNameList = Arrays.stream(playerNames.split(",")).toList();
            Shards shardId = Shards.of(shards);
            PlayerNames names = PlayerNames.from(playerNameList);
            SquadPlayersDto squadPlayersDto = squadService.fetchSquad(shardId, names);

            squadMatchService.fetchSquadMatchResult(squadPlayersDto.squad(), squadPlayersDto.players());
            return ResponseEntity.ok(playerNames + "fetch 성공");
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }
}
