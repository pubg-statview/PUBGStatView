package zoo.pubg.vo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerMatchIdTest {

    private final PlayerMatchId id1 = new PlayerMatchId("1234");
    private final PlayerMatchId id2 = new PlayerMatchId("1234");

    @Test
    @DisplayName("Equals 테스트")
    void equalsTest() {
        assertThat(id1).isEqualTo(id2);
    }

    @Test
    @DisplayName("HashSet 테스트")
    void hashSetTest() {
        // given
        Set<PlayerMatchId> set = new HashSet<>();

        // when
        set.add(id1);

        // then
        assertThat(set.contains(id2)).isEqualTo(true);

    }
}