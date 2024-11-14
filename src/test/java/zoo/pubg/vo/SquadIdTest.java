package zoo.pubg.vo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SquadIdTest {

    @Test
    @DisplayName("squad uuid 테스트")
    void uuidTest() {
        // given
        String[] ids = {"account.1234", "account.1010", "account.2020", "account.3030"};
        SquadId fromArray = SquadId.from(ids);
        SquadId fromList = SquadId.from(Arrays.stream(ids).toList());

        // when & then
        assertThat(fromArray).isEqualTo(fromList);

    }

    @Test
    @DisplayName("squad uuid 테스트 from PlayerIds")
    void uuidFromPlayerIdsTest() {
        // given
        String[] ids = {"account.1234", "account.1010", "account.2020", "account.3030"};
        PlayerIds playerIds = new PlayerIds();
        for (String id : ids) {
            playerIds.add(new PlayerId(id));
        }
        // when
        SquadId fromArray = SquadId.from(ids);
        SquadId fromPlayerIds = SquadId.from(playerIds);

        // then
        assertThat(fromArray).isEqualTo(fromPlayerIds);

    }
}