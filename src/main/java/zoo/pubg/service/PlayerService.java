package zoo.pubg.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.constant.Shards;
import zoo.pubg.domain.Player;
import zoo.pubg.dto.PlayerMatchIdsDto;
import zoo.pubg.exception.NotFoundException;
import zoo.pubg.repository.PlayerRepository;
import zoo.pubg.service.api.PubgBasicApi;
import zoo.pubg.service.parser.PlayerApiParser;
import zoo.pubg.service.parser.deserialization.player.PlayerData;
import zoo.pubg.service.parser.deserialization.player.PlayerDto;
import zoo.pubg.vo.PlayerIds;
import zoo.pubg.vo.PlayerName;
import zoo.pubg.vo.PlayerNames;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayerService {

    private final static PlayerApiParser parser = new PlayerApiParser();

    private final PubgBasicApi pubgBasicAPI;

    @Autowired
    private PlayerRepository playerRepository;

    @Transactional(readOnly = true)
    public Player searchPlayer(PlayerName name) {
        return playerRepository.findByName(name);
    }

    public PlayerMatchIdsDto fetchPlayer(Shards shards, PlayerName name) throws JsonProcessingException {
        String responseString = pubgBasicAPI.fetchPlayerStatsByName(shards.getShardName(), name.getName());
        PlayerDto playerDto = parser.parse(responseString);
        List<PlayerMatchIdsDto> playerMatchIdsDtos = saveAndGetDtos(playerDto);
        return playerMatchIdsDtos.get(0);
    }

    public List<PlayerMatchIdsDto> fetchPlayersByIds(Shards shards, PlayerIds ids) throws JsonProcessingException {
        if (ids.isEmpty()) {
            throw new IllegalArgumentException("");
        }
        if (ids.size() > 10) {
            throw new IllegalArgumentException("10개 이하여야함 (API 제한)");
        }
        String joinId = ids.joinToString();
        String response = pubgBasicAPI.fetchPlayerStatsById(shards.getShardName(), joinId);
        PlayerDto playerDto = parser.parse(response);
        return saveAndGetDtos(playerDto);
    }

    public List<PlayerMatchIdsDto> fetchPlayersByNames(Shards shards, PlayerNames names)
            throws JsonProcessingException {
        if (names.isEmpty()) {
            throw new IllegalArgumentException("");
        }
        if (names.size() > 10) {
            throw new IllegalArgumentException("10개 이하여야함 (API 제한)");
        }
        String joinId = names.joinToString();
        String response = pubgBasicAPI.fetchPlayerStatsByName(shards.getShardName(), joinId);
        PlayerDto playerDto = parser.parse(response);
        List<PlayerMatchIdsDto> playerMatchIdsDtos = saveAndGetDtos(playerDto);
        validResults(names,
                new PlayerNames(playerMatchIdsDtos.stream().map(PlayerMatchIdsDto::getPlayerName).toList()));
        return playerMatchIdsDtos;
    }

    private List<PlayerMatchIdsDto> saveAndGetDtos(PlayerDto playerDto) {
        List<PlayerMatchIdsDto> dtos = new ArrayList<>();

        for (PlayerData data : playerDto.data()) {
            Player player = data.toEntity();
            playerRepository.save(player);
            dtos.add(PlayerMatchIdsDto.from(data));
        }

        return dtos;
    }

    private void validResults(PlayerNames expected, PlayerNames found) {
        PlayerNames unfound = PlayerNames.emptyList();
        for (PlayerName e : expected.playerNames()) {
            if (!found.contains(e)) {
                unfound.add(e);
            }
        }
        if (!unfound.isEmpty()) {
            throw new NotFoundException(unfound.joinToString());
        }
    }
}
