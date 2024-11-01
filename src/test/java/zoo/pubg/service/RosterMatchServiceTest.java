package zoo.pubg.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.domain.Match;
import zoo.pubg.service.dto.RosterDto;
import zoo.pubg.vo.PlayerMatchId;
import zoo.pubg.vo.RosterId;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class RosterMatchServiceTest {

    @Autowired
    private RosterMatchService rosterMatchService;

    @Test
    @DisplayName("roster service 테스트")
    void fetchTest() {
        // given
        Match match = new Match();
        List<RosterDto> rosterDtos = createRosterDtos(
                List.of("testRoster1", "testRoster2", "testRoster3", "testRoster4"));

        // when
        PlayerRosterMap map = rosterMatchService.fetch(match, rosterDtos);

        // then
        assertThat(map.size()).isEqualTo(rosterDtos.size() * 4);

    }

    private List<RosterDto> createRosterDtos(List<String> rosterIds) {
        List<RosterDto> dtos = new ArrayList<>();

        for (int i = 0; i < rosterIds.size(); i++) {
            String rosterId = rosterIds.get(i);
            dtos.add(new RosterDto(
                    new RosterId(rosterId), i, i, createId(rosterId)
            ));
        }

        return dtos;
    }

    private List<PlayerMatchId> createId(String id) {
        return List.of(
                new PlayerMatchId(id + 1),
                new PlayerMatchId(id + 2),
                new PlayerMatchId(id + 3),
                new PlayerMatchId(id + 4)
        );
    }
}