package zoo.pubg.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class PlayerServiceTest {

    @Autowired
    private PlayerService playerService;

    @Test
    @DisplayName("player 기본 API 호출 테스트")
    void fetchPlayerStats() {
        // given
        String shards = "kakao";
        String name = "Lil_Ziu__Vert";

        // when
        String s = playerService.fetchPlayerStats(shards, name);
        System.out.println(s);

    }
}