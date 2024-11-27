package zoo.pubg.service;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.constant.Shards;
import zoo.pubg.domain.Match;
import zoo.pubg.domain.Player;
import zoo.pubg.domain.PlayerMatchResult;
import zoo.pubg.domain.RosterMatchResult;
import zoo.pubg.domain.Squad;
import zoo.pubg.domain.SquadMatchResult;
import zoo.pubg.domain.list.Players;
import zoo.pubg.factory.MatchGenerator;
import zoo.pubg.factory.PlayerGenerator;
import zoo.pubg.factory.PlayerMatchResultGenerator;
import zoo.pubg.factory.RosterMatchResultGenerator;
import zoo.pubg.factory.SquadGenerator;
import zoo.pubg.repository.SquadMatchRepository;
import zoo.pubg.vo.MatchId;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class SquadMatchServiceTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private SquadMatchService squadMatchService;

    @Autowired
    private SquadMatchRepository squadMatchRepository;

    private Players players;
    private Squad squad;
    private Match match1;
    private Match match2;
    private RosterMatchResult rosterMatchResult1;
    private RosterMatchResult rosterMatchResult2;
    private List<PlayerMatchResult> playerMatchResults1;
    private List<PlayerMatchResult> playerMatchResults2;

    @BeforeEach
    void setUp() {
        players = PlayerGenerator.generatePlayers(4);
        squad = SquadGenerator.generate(players);
        match1 = MatchGenerator.generateMatch();
        match2 = MatchGenerator.generateMatch();
        rosterMatchResult1 = RosterMatchResultGenerator.generate(match1);
        rosterMatchResult2 = RosterMatchResultGenerator.generate(match2);
        playerMatchResults1 = PlayerMatchResultGenerator.generateResults(players, match1, rosterMatchResult1);
        playerMatchResults2 = PlayerMatchResultGenerator.generateResults(players, match2, rosterMatchResult2);

        for (Player player : players) {
            em.persist(player);
        }

        em.persist(squad);
        em.persist(match1);
        em.persist(match2);
        em.persist(rosterMatchResult1);
        em.persist(rosterMatchResult2);

        for (int i = 0; i < 4; i++) {
            em.persist(playerMatchResults1.get(i));
            em.persist(playerMatchResults2.get(i));
        }
    }

    @Test
    @DisplayName("스쿼드 매치 결과 패치 테스트")
    void fetchSquadMatchTest() {
        // given
        // setup

        // when
        squadMatchService.fetchSquadMatchResult(squad, players);

        List<SquadMatchResult> foundBySquad = squadMatchRepository.findBy(squad);
        List<SquadMatchResult> foundByMatch1 = squadMatchRepository.findBy(match1);

        // then
        assertThat(foundBySquad).hasSize(2);
        assertThat(foundByMatch1).hasSize(1);
        assertThat(foundByMatch1.get(0).getData().winPlace()).isEqualTo(rosterMatchResult1.getRank());

        long diffSeconds = ChronoUnit.SECONDS.between(squad.getLastUpdated(), LocalDateTime.now());
        assertThat(diffSeconds).isLessThan(300);
    }

    @Test
    @DisplayName("lastUpdated 이후 데이터들은 불러와지지 않아야 한다.")
    void beforeLastUpdateTest() {
        // given
        Match oldMatch = new Match(
                new MatchId("testMatch-test01"), "testMap", "testMode",
                "testType", Shards.KAKAO, 1000, "testUrl",
                LocalDateTime.of(2000, 1, 1, 0, 0, 0, 0)
        );
        RosterMatchResult oldRoster = RosterMatchResultGenerator.generate(oldMatch);
        List<PlayerMatchResult> oldPlayerMatch = PlayerMatchResultGenerator.generateResults(
                players, oldMatch, oldRoster);
        em.persist(oldMatch);
        em.persist(oldRoster);
        for (PlayerMatchResult playerMatch : oldPlayerMatch) {
            em.persist(playerMatch);
        }

        // when
        squadMatchService.fetchSquadMatchResult(squad, players);

        List<SquadMatchResult> recentSquadMatch = squadMatchRepository.findBy(match1);
        List<SquadMatchResult> oldSquadMatch = squadMatchRepository.findBy(oldMatch);

        // then
        assertThat(recentSquadMatch).hasSize(1);
        assertThat(oldSquadMatch).isEmpty();
    }

    @Test
    @DisplayName("squad 기록 pagination 테스트")
    void squadPaginationTest() {
        // given
        int page = 0;
        int size = 5;
        squadMatchService.fetchSquadMatchResult(squad, players);

        // when
        Page<SquadMatchResult> resultPage = squadMatchService.findAllWithPagination(squad, page, size);

        // then
        assertThat(resultPage).isNotNull();
        assertThat(resultPage.getContent().size()).isEqualTo(2);
        assertThat(resultPage.getContent())
                .extracting(result -> result.getMatch().getCreatedAt())
                .isSortedAccordingTo(Comparator.reverseOrder());
    }
}