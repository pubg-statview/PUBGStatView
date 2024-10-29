package zoo.pubg.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.constant.Shards;
import zoo.pubg.vo.MatchId;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MatchServiceTest {

    @Autowired
    private MatchService matchService;

    @Test
    @DisplayName("player 기본 API 호출 테스트")
    void fetchMatchHistoryTest() throws JsonProcessingException {
        Shards shards = Shards.KAKAO;
        MatchId matchId = new MatchId("b79c05af-bfb3-4744-b9d2-9ccdbe4c0746");

        matchService.fetchMatchHistory(shards, matchId);
    }
}