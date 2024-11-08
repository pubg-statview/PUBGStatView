package zoo.pubg.repository;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.constant.PlayerType;
import zoo.pubg.constant.Shards;
import zoo.pubg.domain.Player;
import zoo.pubg.domain.rank.Rank;
import zoo.pubg.domain.rank.RankedDetails;
import zoo.pubg.domain.rank.Season;
import zoo.pubg.domain.rank.Tier;
import zoo.pubg.vo.PlayerId;
import zoo.pubg.vo.PlayerName;
import zoo.pubg.vo.RankStatisticalData;
import zoo.pubg.vo.SeasonId;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class RankRepositoryTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private RankRepository rankRepository;

    private Player player;
    private Season season;
    private Rank rank;

    @BeforeEach
    void setUp() {
        player = new Player(
                new PlayerId("account.test1234"), new PlayerName("testPlayer"), Shards.KAKAO, "clan.test1234clan",
                PlayerType.ETC, LocalDateTime.now()
        );
        season = new Season(
                new SeasonId("season.1"), true, Shards.KAKAO
        );
        rank = new Rank(
                null, "squad-test", season,
                new RankedDetails(
                        null,
                        new RankStatisticalData(
                                0, 0f, 0f, 0f, 0,
                                0, 0f, 0, 0, 0f, 0
                        ),
                        new Tier(
                                null,
                                "Gold", "5", 2400,
                                "Gold", "1", 2800
                        )
                ), player
        );
        em.persist(player);
        em.persist(season);
    }

    @Test
    @DisplayName("rank 저장 및 검색 테스트")
    void saveTest() {
        // given
        rankRepository.save(rank);

        // when & then
        Rank result = rankRepository.findBy(player, season);

        Tier tier = result.getRankedDetails().getTier();

        assertThat(tier.getCurrentTier()).isEqualTo("Gold");
    }

    @Test
    @DisplayName("이미 해당 시즌의 플레이어 랭크 데이터가 있다면, 업데이트")
    void updateTest() {
        // given
        em.persist(rank);
        Rank rankFromApi = new Rank(
                null, "squad-test", season,
                new RankedDetails(
                        null,
                        new RankStatisticalData(
                                10, 0f, 0f, 0f, 0,
                                0, 0f, 0, 0, 0f, 0
                        ),
                        new Tier(
                                null,
                                "Platinum", "5", 3000,
                                "Platinum", "5", 3000
                        )
                ), player
        );

        // when
        rankRepository.save(rankFromApi);
        Rank updatedRank = rankRepository.findBy(player, season);
        int roundsPlayed = updatedRank.getRankedDetails().getRankStatisticalData().roundsPlayed();
        String currentTier = updatedRank.getRankedDetails().getTier().getCurrentTier();

        // then
        assertThat(roundsPlayed).isEqualTo(10);
        assertThat(currentTier).isEqualTo("Platinum");

    }
}
