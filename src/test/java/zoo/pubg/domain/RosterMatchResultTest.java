package zoo.pubg.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.HashSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import zoo.pubg.constant.Shards;
import zoo.pubg.vo.MatchId;
import zoo.pubg.vo.RosterId;

class RosterMatchResultTest {

    private Match match;

    @BeforeEach
    void setUp() {
        match = new Match(
                new MatchId("123123"),
                "mapName", "gameMode", "matchType",
                Shards.KAKAO, 1000, "telemetry", LocalDateTime.now()
        );
    }

    @Test
    @DisplayName("equals, hash 테스트")
    void equalsAndHashTest() {
        // given
        RosterMatchResult result1 = new RosterMatchResult(
                new RosterId("123"), 1, 1, match
        );
        RosterMatchResult result2 = new RosterMatchResult(
                new RosterId("123"), 1, 1, match
        );

        // when
        HashSet<RosterMatchResult> resultSet = new HashSet<>();
        resultSet.add(result1);
        resultSet.add(result2);

        // then
        assertThat(result1).isEqualTo(result2);
        assertThat(resultSet.size()).isEqualTo(1);
    }
}