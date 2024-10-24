package zoo.pubg.service;

import com.fasterxml.jackson.core.JsonProcessingException;
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
class PlayerMatchServiceTest {

    @Autowired
    private PlayerMatchService playerMatchService;

    @Test
    @DisplayName("player 기본 API 호출 테스트")
    void fetchPlayerStats() throws JsonProcessingException {
        // given
        String shards = "kakao";
        String matchId = "b79c05af-bfb3-4744-b9d2-9ccdbe4c0746";

        // when
        String s = playerMatchService.fetchMatchHistory(shards, matchId);
        System.out.println(s);

    }
}