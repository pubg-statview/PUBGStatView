package zoo.pubg.repository;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.constant.Shards;
import zoo.pubg.domain.Match;
import zoo.pubg.domain.RosterMatchResult;
import zoo.pubg.vo.MatchId;
import zoo.pubg.vo.RosterId;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class RosterMatchRepositoryTest {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private RosterMatchRepository rosterMatchRepository;

    @Test
    @DisplayName("RosterMatch 저장 테스트")
    void saveTest() {
        // given
        Match match = new Match(
                new MatchId("b79c05af-bfb3-4744-b9d2-9ccdbe4c0746"),
                "Baltic_Main", "squad", "official",
                Shards.KAKAO, 1531, "123123", LocalDateTime.now()
        );
        matchRepository.save(match);
        RosterMatchResult rosterMatchResult = new RosterMatchResult(
                new RosterId("3b140aa9-b86a-42d3-9dbc-c60cf8a7b2cb"),
                23, 18, match
        );

        // when
        rosterMatchRepository.save(rosterMatchResult);

    }
}