package zoo.pubg.repository;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
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
    @DisplayName("현재 시즌만 불러오는 기능 테스트")
    @Sql(statements = "DELETE FROM Season")
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
        Season currentSeason = seasonRepository.findCurrentSeason(Shards.KAKAO);

        // then
        assertThat(currentSeason.getSeasonId()).isEqualTo(currentSeasonId);
        assertThat(currentSeason.getIsCurrentSeason()).isEqualTo(true);

    }
}
