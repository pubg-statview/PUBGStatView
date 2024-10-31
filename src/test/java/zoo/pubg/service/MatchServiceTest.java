package zoo.pubg.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.constant.Shards;
import zoo.pubg.dto.MatchIdsDto;
import zoo.pubg.vo.MatchId;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MatchServiceTest {

    @Autowired
    private MatchService matchService;

    @Test
    @DisplayName("match API 호출 테스트")
    void fetchMatchHistoryTest() throws JsonProcessingException {
        Shards shards = Shards.KAKAO;
        MatchId matchId = new MatchId("b79c05af-bfb3-4744-b9d2-9ccdbe4c0746");

        matchService.fetchMatchHistory(shards, matchId);
    }

    @Test
    @DisplayName("여러 매치에 대해서 API 호출")
    void fetchMatches() {
        // given
        Shards shards = Shards.KAKAO;
        MatchIdsDto dto = new MatchIdsDto(
                shards,
                List.of(
                        new MatchId("b614ec6c-3bf1-405b-aca7-2ed5f7a12a6b"),
                        new MatchId("6d9d9868-023b-466b-8477-80c6cb21e0e0"),
                        new MatchId("e5429144-bb54-4f75-b763-f74e7452bbb7"),
                        new MatchId("6dc8c161-e400-4ac1-a977-5f93e68fde9f")
                )
        );

        // when
        matchService.fetchMatches(dto);

    }
}