package zoo.pubg.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.constant.Shards;
import zoo.pubg.domain.Player;
import zoo.pubg.exception.NotFoundException;
import zoo.pubg.repository.PlayerRepository;
import zoo.pubg.service.api.PubgBasicApi;
import zoo.pubg.vo.PlayerName;
import zoo.pubg.vo.PlayerNames;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class PlayerServiceMockTest {

    private final static String jsonFormat = """
              {
              "data": [
                {
                  "type": "player",
                  "id": "account.test1234",
                  "attributes": {
                    "patchVersion": "",
                    "banType": "Innocent",
                    "clanId": "clan.test1234",
                    "name": "ttest1",
                    "stats": null,
                    "titleId": "pubg",
                    "shardId": "kakao"
                  },
                  "relationships": {
                    "assets": {
                      "data": []
                    },
                    "matches": {
                      "data": [
                        {
                          "type": "match",
                          "id": "4fec46b0-d7b0-42a8-9e28-0b4731dc79f4"
                        },
                        {
                          "type": "match",
                          "id": "39107321-f066-4484-bedb-e3c1aee38145"
                        }
                      ]
                    }
                  }
                },
                {
                  "type": "player",
                  "id": "account.test12345678",
                  "attributes": {
                    "patchVersion": "",
                    "banType": "Innocent",
                    "clanId": "clan.test1234",
                    "name": "ttest2",
                    "stats": null,
                    "titleId": "pubg",
                    "shardId": "kakao"
                  },
                  "relationships": {
                    "assets": {
                      "data": []
                    },
                    "matches": {
                      "data": [
                        {
                          "type": "match",
                          "id": "4fec46b0-d7b0-42a8-9e28-0b4731dc79f4"
                        },
                        {
                          "type": "match",
                          "id": "39107321-f066-4484-bedb-e3c1aee38145"
                        }
                      ]
                    }
                  }
                }
              ]
            }
            """;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerRepository playerRepository;

    @MockBean
    private PubgBasicApi pubgBasicApi;

    @Test
    @DisplayName("여러명의 player 동시 조회 테스트 (이름)")
    void fetchPlayersByNameTest() throws JsonProcessingException {
        // given
        List<String> names = List.of(
                "ttest1", "ttest2"
        );
        Shards shards = Shards.KAKAO;
        PlayerNames playerNames = PlayerNames.from(names);

        when(pubgBasicApi.fetchPlayerStatsByName(shards.getShardName(), playerNames.joinToString()))
                .thenReturn(jsonFormat);

        playerService.fetchPlayersByNames(shards, PlayerNames.from(names));

        for (String name : names) {
            PlayerName playerName = new PlayerName(name);
            Player player = playerRepository.findByName(playerName);
            assertThat(playerName).isEqualTo(player.getName());
        }
    }

    @Test
    @DisplayName("여러명의 player 동시 조회 테스트 (이름) - API 응답에 없는 이름이 있을 시 에러")
    void fetchPlayersByNameExceptionTest() throws JsonProcessingException {
        // given
        List<String> names = List.of(
                "ttest1", "ttest2", "account.1234"
        );
        Shards shards = Shards.KAKAO;
        PlayerNames playerNames = PlayerNames.from(names);

        when(pubgBasicApi.fetchPlayerStatsByName(shards.getShardName(), playerNames.joinToString()))
                .thenReturn(jsonFormat);

        assertThrows(
                NotFoundException.class,
                () -> playerService.fetchPlayersByNames(shards, PlayerNames.from(names))
        ).printStackTrace();
    }
}
