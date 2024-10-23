package zoo.pubg.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class PlayerServiceTest {

    @Autowired
    private PlayerService playerService;

    @Test
    @Commit
    @DisplayName("player 기본 API 호출 테스트")
    void fetchPlayerStats() throws JsonProcessingException {
        // given
        String shards = "kakao";
        String name = "Lil_Ziu__Vert";

        // when
        String s = playerService.fetchPlayerStats(shards, name);
        System.out.println(s);

    }
}