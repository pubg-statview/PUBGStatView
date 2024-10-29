package zoo.pubg.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zoo.pubg.constant.Shards;
import zoo.pubg.domain.Match;
import zoo.pubg.domain.Player;
import zoo.pubg.domain.PlayerMatchResult;
import zoo.pubg.domain.RosterMatchResult;
import zoo.pubg.repository.PlayerMatchRepository;
import zoo.pubg.repository.PlayerRepository;
import zoo.pubg.service.dto.ParticipantDto;
import zoo.pubg.vo.PlayerId;
import zoo.pubg.vo.PlayerIds;

@Service
public class PlayerMatchService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerMatchRepository playerMatchRepository;

    @Autowired
    private PlayerService playerService;

    public void fetch(Match match, List<ParticipantDto> participantDtos) throws JsonProcessingException {
        participantDtos = participantDtos.stream()
                .filter(p -> p.playerId().getPlayerId().split("\\.")[0].equals("account"))
                .toList();  // 일반 봇 임시로 처리
        fetchUnregisterPlayer(match.getShardId(), participantDtos);
        participantDtos.forEach(dto -> fetch(match, dto));
    }

    private void fetch(Match match, ParticipantDto participantDto) {
        Player player = playerRepository.find(participantDto.playerId());
        PlayerMatchResult playerMatchResult = new PlayerMatchResult(
                participantDto.playerMatchId(), player, match, new RosterMatchResult(),
                participantDto.dbno(), participantDto.damageDealt(),
                participantDto.headshotKills(), participantDto.kills(), participantDto.assists(),
                participantDto.longestKill(), participantDto.winPlace()
        );
        playerMatchRepository.save(playerMatchResult);
    }

    private void fetchUnregisterPlayer(Shards shards, List<ParticipantDto> participantDtos)
            throws JsonProcessingException {
        PlayerIds unregisterIds = new PlayerIds();
        for (ParticipantDto participantDto : participantDtos) {
            PlayerId id = participantDto.playerId();
            if (playerRepository.isExistsId(id)) {
                continue;
            }
            unregisterIds.add(id);
            if (unregisterIds.size() == 10) {
                playerService.fetchPlayersByIds(shards, unregisterIds);
                unregisterIds.clear();
            }
        }
        if (unregisterIds.size() <= 10) {
            playerService.fetchPlayersByIds(shards, unregisterIds);
        }
    }
}
