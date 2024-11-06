package zoo.pubg.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.domain.rank.Season;
import zoo.pubg.vo.SeasonId;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class SeasonRepositoryTest {

    @Autowired
    private SeasonRepository seasonRepository;

    @Test
    @DisplayName("같은 시즌 아이디가 들어왔을 경우, 새로운 객체로 업데이트")
    void newSeasontest() {
        // given
        SeasonId id = new SeasonId("123");
        Season origin = new Season(
                id, true
        );
        Season changed = new Season(
                id, false
        );

        // when
        seasonRepository.save(origin);
        seasonRepository.save(changed);

        // then
        Season updatedSeason = seasonRepository.find(id);
        assertThat(updatedSeason.getIsCurrentSeason()).isEqualTo(changed.getIsCurrentSeason());

    }

    @Test
    @DisplayName("현재 시즌만 불러오는 기능 테스트")
    void currentSeasonTest() {
        // given
        SeasonId currentSeasonId = new SeasonId("1");
        List<Season> seasons = List.of(
                new Season(currentSeasonId, true),
                new Season(new SeasonId("2"), false),
                new Season(new SeasonId("3"), false)
        );
        seasons.forEach(seasonRepository::save);

        // when
        Season currentSeason = seasonRepository.findCurrentSeason();

        // then
        assertThat(currentSeason.getId()).isEqualTo(currentSeasonId);
        assertThat(currentSeason.getIsCurrentSeason()).isEqualTo(true);

    }
}
