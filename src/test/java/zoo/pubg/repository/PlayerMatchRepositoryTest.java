package zoo.pubg.repository;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
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
import zoo.pubg.constant.PlayerType;
import zoo.pubg.constant.Shards;
import zoo.pubg.domain.Match;
import zoo.pubg.domain.Player;
import zoo.pubg.domain.PlayerMatchResult;
import zoo.pubg.domain.RosterMatchResult;
import zoo.pubg.vo.MatchId;
import zoo.pubg.vo.PlayerId;
import zoo.pubg.vo.PlayerMatchId;
import zoo.pubg.vo.PlayerName;
import zoo.pubg.vo.RosterId;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class PlayerMatchRepositoryTest {

    @Autowired
    private PlayerMatchRepository playerMatchRepository;

    @Autowired
    private EntityManager em;

    private Player testPlayer;

    @BeforeEach
    void setUp() {
        testPlayer = new Player(
                new PlayerId("account.abbb"),
                new PlayerName("testPlayer"),
                Shards.KAKAO, "clan.asdfasdf", PlayerType.ETC, LocalDateTime.now()
        );
        em.persist(testPlayer);

        for (int i = 0; i < 15; i++) {
            Match match = new Match(
                    new MatchId("testMatch" + i),
                    "testMap", "testGameMode", "testGameMatchType",
                    Shards.KAKAO, 1100, "testTelemetry", LocalDateTime.now()
            );
            em.persist(match);

            RosterMatchResult rosterMatchResult = new RosterMatchResult(
                    new RosterId("testRosterId" + i), i, i, match);
            em.persist(rosterMatchResult);

            PlayerMatchResult playerMatchResult = new PlayerMatchResult(
                    new PlayerMatchId("testMatch" + i),
                    testPlayer, match, rosterMatchResult,
                    0, 0.0f, 0, 0, 0, 0.0f, 0
            );
            em.persist(playerMatchResult);
        }

        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("Match 페이지네이션 테스트")
    void matchPagenationTest() {
        // given
        int page = 0;
        int size = 5;

        Page<PlayerMatchResult> resultPage = playerMatchRepository.findAllWithPagination(testPlayer, page, size);

        assertThat(resultPage).isNotNull();
        assertThat(resultPage.getContent().size()).isEqualTo(size);
        assertThat(resultPage.getTotalElements()).isEqualTo(15);
        assertThat(resultPage.getTotalPages()).isEqualTo(3);
        assertThat(resultPage.getContent()).allMatch(
                result -> result.getPlayerId().equals(testPlayer.getPlayerId())
        );

        assertThat(resultPage.getContent())
                .extracting(result -> result.getMatch().getCreatedAt())
                .isSortedAccordingTo(Comparator.reverseOrder());
    }

    @Test
    @DisplayName("Match에 해당하는 모든 플레이어 데이터 불러오기")
    void findAllParticipantsTest() {
        // given
        Match match = new Match(
                new MatchId("testMatch" + 1),
                "testMap", "testGameMode", "testGameMatchType",
                Shards.KAKAO, 0, "testTelemetry", LocalDateTime.now()
        );

        // when
        List<PlayerMatchResult> allParticipants = playerMatchRepository.findAllParticipants(match);

        // then
        assertThat(allParticipants.size()).isEqualTo(1);
    }
}