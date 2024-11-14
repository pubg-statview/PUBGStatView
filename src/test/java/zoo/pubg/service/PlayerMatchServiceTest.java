package zoo.pubg.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.time.LocalDateTime;
import java.util.List;
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
import zoo.pubg.repository.MatchRepository;
import zoo.pubg.service.dto.ParticipantDto;
import zoo.pubg.service.map.PlayerRosterMap;
import zoo.pubg.vo.MatchId;
import zoo.pubg.vo.PlayerId;
import zoo.pubg.vo.PlayerMatchId;
import zoo.pubg.vo.PlayerName;
import zoo.pubg.vo.RosterId;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class PlayerMatchServiceTest {
    private final List<String> ids = List.of("account.cf2acac6cb9741fba202f293aeaab56d",
            "account.0af3e703b89040d08c1eda37718ca4ac",
            "account.2e66f4587ca7483fba08dc7eeea5c5cf",
            "account.a028088f9ff841bba04f27bf45bc6250",
            "account.a164c65de7bd46f3a0101d4b8ad4a626",
            "account.816d091773b940de84eefdbc2dc0e22a",
            "account.57475652769448ccaa426450a14b2643",
            "account.54f60cff5a04416a9d3a006c3fe88b3b",
            "account.3df7ccda72e44db19684387d0f7c4aac",
            "account.f5ec431e1b3b47238078bf8816f26392",
            "account.5e5c477b1e264abf8453ba55918c384a",
            "account.453c56d4569f4e6ebdbf49f7961097f7",
            "account.7e5a8385bd26429d865f04dcd92abeb3",
            "account.78e27aff8e3c4e4ca9a5765f1f5e5895",
            "account.a93fff96180b4826a1560264c547fb45",
            "account.6e7f66d9bb8e4f2abd954183980e8ccd",
            "account.ba47b1fbc8564938af0132cc40a5b324"
    );

    private final Match match = new Match(
            new MatchId("90cc5f77-5d9a-4473-88b4-e55659120632"), "Baltic_Main",
            "squad", "competitive",
            Shards.KAKAO, 1916, "url", LocalDateTime.now()
    );

    @Autowired
    private PlayerMatchService service;
    @Autowired
    private MatchRepository repository;

    @Test
    @DisplayName("여러명의 id가 들어왔을 때, 이미 있는 id는 스킵하고 잘 처리하는 지 테스트")
    void fetchTest() throws JsonProcessingException {
        repository.save(match);
        service.fetch(match, makeInput(ids), makeMap());
    }

    private static List<ParticipantDto> makeInput(List<String> ids) {
        return ids.stream().map(PlayerMatchServiceTest::makeDto).toList();
    }

    private static ParticipantDto makeDto(String id) {
        return new ParticipantDto(new PlayerMatchId("test" + id), new PlayerId(id), new PlayerName(id),
                0, 0, 0, 0, 0, 0, 0);
    }

    private PlayerRosterMap makeMap() {
        PlayerRosterMap map = new PlayerRosterMap();
        ids.forEach(id -> map.put(new PlayerMatchId("test" + id),
                new RosterMatchResult(new RosterId("roster" + id), 0, 0, match)));
        return map;
    }
}