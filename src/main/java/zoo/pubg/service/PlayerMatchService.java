package zoo.pubg.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zoo.pubg.domain.Match;
import zoo.pubg.domain.Player;
import zoo.pubg.domain.PlayerMatchResult;
import zoo.pubg.repository.PlayerMatchRepository;
import zoo.pubg.repository.PlayerRepository;
import zoo.pubg.service.dto.ParticipantDto;

@Service
public class PlayerMatchService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerMatchRepository playerMatchRepository;

    @Autowired
    private PlayerService playerService;

    public void fetch(Match match, List<ParticipantDto> participantDtos) throws JsonProcessingException {
        fetchUnregisterPlayer(match.getShardId(), participantDtos);
        participantDtos.forEach(dto -> fetch(match, dto));
    }

    private void fetch(Match match, ParticipantDto participantDto) {
        Player player = playerRepository.find(participantDto.playerId());
        PlayerMatchResult playerMatchResult = new PlayerMatchResult(
                participantDto.playerMatchId(), player, match,
                participantDto.dbno(), participantDto.damageDealt(),
                participantDto.headshotKills(), participantDto.kills(), participantDto.assists(),
                participantDto.longestKill(), participantDto.winPlace()
        );
        playerMatchRepository.save(playerMatchResult);
    }

    private void fetchUnregisterPlayer(String shards, List<ParticipantDto> participantDtos)
            throws JsonProcessingException {
        List<String> unregisterIds = new ArrayList<>();
        for (ParticipantDto participantDto : participantDtos) {
            String id = participantDto.playerId();
            if (playerRepository.isExistsId(id)) {
                continue;
            }
            unregisterIds.add(id);
            if (unregisterIds.size() == 10) {
                playerService.fetchPlayers(shards, unregisterIds);
                unregisterIds.clear();
            }
        }
        if (unregisterIds.size() <= 10) {
            playerService.fetchPlayers(shards, unregisterIds);
        }
    }
}
