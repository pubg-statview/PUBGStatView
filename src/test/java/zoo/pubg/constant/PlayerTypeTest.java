package zoo.pubg.constant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import zoo.pubg.vo.PlayerId;

class PlayerTypeTest {

    private static Stream<Arguments> providePlayerType() {
        return Stream.of(
                arguments("account.edee507a1563439e82ea9cfd60039b9a", PlayerType.PLAYER),
                arguments("ai.123", PlayerType.BOT),
                arguments("account.", PlayerType.ETC),
                arguments("ai.", PlayerType.ETC),
                arguments("ai.123456", PlayerType.ETC),
                arguments("1234", PlayerType.ETC)
        );
    }

    @ParameterizedTest
    @DisplayName("PlayerId로 플레이어 분류")
    @MethodSource("providePlayerType")
    void fromTest(String id, PlayerType answer) {
        // given
        PlayerId playerId = new PlayerId(id);

        // when
        PlayerType type = PlayerType.from(playerId);

        // then
        assertThat(type).isEqualTo(answer);
    }
}