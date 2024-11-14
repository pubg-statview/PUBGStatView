package zoo.pubg.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.constant.Shards;
import zoo.pubg.exception.NotFoundException;
import zoo.pubg.exception.TooManyRequestsException;
import zoo.pubg.vo.PlayerIds;
import zoo.pubg.vo.PlayerName;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class PlayerServiceTest {

    @Autowired
    private PlayerService playerService;

    @Test
    @DisplayName("Player 이름으로 조회하기")
    void fetchPlayerTest() throws JsonProcessingException {
        PlayerName playerName = new PlayerName("1000zoo");
        Shards shards = Shards.STEAM;

        playerService.fetchPlayer(shards, playerName);
    }

    @Test
    @DisplayName("없는 이름으로 조회하기")
    void fetchWrongPlayerTest() throws JsonProcessingException {
        PlayerName playerName = new PlayerName("asdfwew111");
        Shards shards = Shards.STEAM;

        assertThrows(
                NotFoundException.class,
                () -> playerService.fetchPlayer(shards, playerName)
        ).printStackTrace();
    }

    @Test
    @DisplayName("너무 많은 요청이 들어왔을 때")
    @Disabled
    void fetchManyPlayerTest() throws JsonProcessingException {
        PlayerName playerName = new PlayerName("Lil_Ziu__Vert");
        Shards shards = Shards.KAKAO;
        int times = 20;

        assertThrows(
                TooManyRequestsException.class,
                () -> {
                    for (int i = 0; i < times; i++) {
                        playerService.fetchPlayer(shards, playerName);
                    }
                }
        );
    }

    @Test
    @DisplayName("여러명의 player 동시 조회 테스트")
    void fetchPlayersTest() throws JsonProcessingException {
        // given
        List<String> ids = List.of("account.cf2acac6cb9741fba202f293aeaab56d",
                "account.0af3e703b89040d08c1eda37718ca4ac",
                "account.2e66f4587ca7483fba08dc7eeea5c5cf",
                "account.a028088f9ff841bba04f27bf45bc6250",
                "account.a164c65de7bd46f3a0101d4b8ad4a626",
                "account.816d091773b940de84eefdbc2dc0e22a",
                "account.57475652769448ccaa426450a14b2643",
                "account.54f60cff5a04416a9d3a006c3fe88b3b",
                "account.3df7ccda72e44db19684387d0f7c4aac"
        );
        Shards shards = Shards.KAKAO;

        playerService.fetchPlayersByIds(shards, PlayerIds.from(ids));
    }
}