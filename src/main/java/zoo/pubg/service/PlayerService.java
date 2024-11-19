package zoo.pubg.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.constant.Shards;
import zoo.pubg.domain.Player;
import zoo.pubg.dto.PlayerMatchIdsDto;
import zoo.pubg.exception.NotFoundException;
import zoo.pubg.repository.PlayerRepository;
import zoo.pubg.vo.PlayerName;
import zoo.pubg.vo.list.PlayerIds;
import zoo.pubg.vo.list.PlayerNames;
import zoo.pubg.vo.list.ValueList;

@Service
@Transactional
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerApiService playerApiService;

    @Transactional(readOnly = true)
    public Player searchPlayer(PlayerName name) {
        return playerRepository.findByName(name);
    }

    public PlayerMatchIdsDto fetchPlayer(Shards shards, PlayerName name) throws JsonProcessingException {
        PlayerMatchIdsDto playerMatchIdsDto = playerApiService.fetchPlayer(shards, name);
        playerRepository.save(playerMatchIdsDto.player());
        return playerMatchIdsDto;
    }

    public List<PlayerMatchIdsDto> fetchPlayersByIds(Shards shards, PlayerIds ids) throws JsonProcessingException {
        validList(ids);
        List<PlayerMatchIdsDto> playerMatchIdsDtos = playerApiService.fetchPlayersByIds(shards, ids);
        playerMatchIdsDtos.forEach(dto -> playerRepository.save(dto.player()));
        return playerMatchIdsDtos;
    }

    public List<PlayerMatchIdsDto> fetchPlayersByNames(Shards shards, PlayerNames names)
            throws JsonProcessingException {
        validList(names);
        List<PlayerMatchIdsDto> playerMatchIdsDtos = playerApiService.fetchPlayersByNames(shards, names);
        playerMatchIdsDtos.forEach(dto -> playerRepository.save(dto.player()));
        validResults(names,
                new PlayerNames(playerMatchIdsDtos.stream().map(PlayerMatchIdsDto::getPlayerName).toList()));
        return playerMatchIdsDtos;
    }

    private void validList(ValueList<?> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("");
        }
        if (list.size() > 10) {
            throw new IllegalArgumentException("10개 이하여야함 (API 제한)");
        }
    }

    private void validResults(PlayerNames expected, PlayerNames found) {
        PlayerNames unfound = new PlayerNames();
        for (PlayerName e : expected.getList()) {
            if (!found.contains(e)) {
                unfound.add(e);
            }
        }
        if (!unfound.isEmpty()) {
            throw new NotFoundException(unfound.joinToString());
        }
    }
}
