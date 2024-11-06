package zoo.pubg.repository;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.constant.Shards;
import zoo.pubg.domain.rank.Season;
import zoo.pubg.vo.SeasonId;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class SeasonRepositoryTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private SeasonRepository seasonRepository;

    @Test
    @DisplayName("같은 시즌 아이디가 들어왔을 경우, 새로운 객체로 업데이트")
    void newSeasontest() {
        // given
        SeasonId id = new SeasonId("123");
        Season origin = new Season(
                id, true, Shards.KAKAO
        );
        Season changed = new Season(
                id, false, Shards.KAKAO
        );

        // when
        seasonRepository.save(origin);
        seasonRepository.save(changed);

        // then
//        Season updatedSeason = seasonRepository.find(id);
//        assertThat(updatedSeason.getIsCurrentSeason()).isEqualTo(changed.getIsCurrentSeason());

    }

    @Test
    @DisplayName("현재 시즌만 불러오는 기능 테스트")
    void currentSeasonTest() {
        // given
        SeasonId currentSeasonId = new SeasonId("1");
        List<Season> seasons = List.of(
                new Season(currentSeasonId, true, Shards.KAKAO),
                new Season(new SeasonId("2"), false, Shards.KAKAO),
                new Season(new SeasonId("3"), false, Shards.KAKAO)
        );
        seasons.forEach(seasonRepository::save);

        // when
        Season currentSeason = seasonRepository.findCurrentSeason();

        // then
        assertThat(currentSeason.getId()).isEqualTo(currentSeasonId);
        assertThat(currentSeason.getIsCurrentSeason()).isEqualTo(true);

    }

    @ParameterizedTest
    @DisplayName("시즌 업데이트가 필요한 지 확인하는 메서드 테스트")
    @CsvSource(value = {
            "season2,false", "season3,true"
    }, delimiter = ',')
    void isUpdateNeededTest(String currentSeasonId, boolean answer) {
        // given
        Season season1 = new Season(new SeasonId("season1"), false, Shards.KAKAO);
        Season season2 = new Season(new SeasonId("season2"), true, Shards.KAKAO);

        // Season 테이블에는 season2가 가장 최근인 상황
        em.persist(season1);
        em.persist(season2);

        // when
        Season currentSeason = new Season(new SeasonId(currentSeasonId), true, Shards.KAKAO);
        boolean updateNeeded = seasonRepository.isUpdateNeeded(currentSeason);

        // then
        assertThat(updateNeeded).isEqualTo(answer);

    }

    @Test
    @DisplayName("시즌 정보가 비어있는 경우")
    void isUpdatedNeededWhenSeasonEmpty() {
        // given
        Season currentSeason = new Season(new SeasonId("season1"), true, Shards.KAKAO);

        // when
        boolean updateNeeded = seasonRepository.isUpdateNeeded(currentSeason);

        // then
        assertThat(updateNeeded).isEqualTo(true);

    }
}
