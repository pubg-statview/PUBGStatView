package zoo.pubg.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import zoo.pubg.factory.MatchGenerator;
import zoo.pubg.vo.MatchId;

class MatchTest {

    private MatchId matchId1 = new MatchId("testMatch.1234");
    private MatchId matchId2 = new MatchId("testMatch.1234");
    private MatchId matchId3 = new MatchId("testMatch.5678");

    @Test
    void testEquals() {
        Match match1 = MatchGenerator.generateMatch(matchId1);
        Match match2 = MatchGenerator.generateMatch(matchId2);
        Match match3 = MatchGenerator.generateMatch(matchId3);

        assertThat(match1).isEqualTo(match2);
        assertThat(match1).isNotEqualTo(match3);
    }

    @Test
    void testHashCode() {
        Match match = MatchGenerator.generateMatch(matchId1);

        assertThat(match.getMatchId().hashCode()).isEqualTo(matchId1.hashCode());
    }

    @Test
    @DisplayName("Hash 테스트")
    void testHashTable() {
        // given
        Set<Match> set = new HashSet<>();

        Match match1 = MatchGenerator.generateMatch(matchId1);
        Match match2 = MatchGenerator.generateMatch(matchId2);

        // when
        set.add(match1);
        set.add(match2);

        // then
        assertThat(set).hasSize(1);
    }
}