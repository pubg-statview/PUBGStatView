package zoo.pubg.vo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerNameTest {

    @ParameterizedTest
    @DisplayName("유효한 닉네임 테스트")
    @ValueSource(strings = {"Lil_Ziu__Vert", "LILll--", "abcK-123"})
    void validNameTest(String name) {
        // when
        PlayerName playerName = new PlayerName(name);

        // then
        assertThat(playerName.name()).isEqualTo(name);

    }

    @ParameterizedTest
    @DisplayName("유효하지 않은 닉네임 예외처리 테스트")
    @ValueSource(strings = {"-jiwoo", "_-jiwoo", "abc", "유효하지않은 이름", "abc def", "AA!!!@#"})
    void invalidNameTest(String name) {

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new PlayerName(name)
        );
    }
}