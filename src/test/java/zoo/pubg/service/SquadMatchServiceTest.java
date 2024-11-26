package zoo.pubg.service;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
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

    @Test
    @DisplayName("스쿼드 매치 결과 패치 테스트")
    void fetchSquadMatchTest() {
        // given
        Players players = PlayerGenerator.generatePlayers(4);
        Squad squad = SquadGenerator.generate(players);
        Match match1 = MatchGenerator.generateMatch();
        Match match2 = MatchGenerator.generateMatch();
        RosterMatchResult rosterMatchResult1 = RosterMatchResultGenerator.generate(match1);
        RosterMatchResult rosterMatchResult2 = RosterMatchResultGenerator.generate(match2);
        List<PlayerMatchResult> playerMatchResults1 = PlayerMatchResultGenerator.generateResults(players, match1,
                rosterMatchResult1);
        List<PlayerMatchResult> playerMatchResults2 = PlayerMatchResultGenerator.generateResults(players, match2,
                rosterMatchResult2);

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

        // when
        squadMatchService.fetchSquadMatchResult(squad, players);

        List<SquadMatchResult> foundBySquad = squadMatchRepository.findBy(squad);
        List<SquadMatchResult> foundByMatch1 = squadMatchRepository.findBy(match1);

        // then
        assertThat(foundBySquad).hasSize(2);
        assertThat(foundByMatch1).hasSize(1);
        assertThat(foundByMatch1.get(0).getData().winPlace()).isEqualTo(1);
    }
}