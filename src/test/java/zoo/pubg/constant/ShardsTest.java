package zoo.pubg.constant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ShardsTest {

    @Test
    @DisplayName("Enum 테스트")
    void validShardTest() {
        // given
        String shardsName = "kakao";

        //when
        Shards shards = Shards.of(shardsName);

        //then
        assertThat(shards).isEqualTo(Shards.KAKAO);
    }

    @Test
    @DisplayName("invalid value 테스트")
    void invalidShardTest() {
        String invalidShardsName = "kkkk";

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> Shards.of(invalidShardsName)
        ).printStackTrace();
    }
}
