package zoo.pubg.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.domain.Match;
import zoo.pubg.domain.Squad;
import zoo.pubg.domain.SquadMatchResult;
import zoo.pubg.domain.list.Players;
import zoo.pubg.factory.MatchGenerator;
import zoo.pubg.factory.PlayerGenerator;
import zoo.pubg.factory.SquadGenerator;
import zoo.pubg.factory.SquadMatchResultGenerator;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class SquadMatchRepositoryTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private SquadMatchRepository squadMatchRepository;

    @Test
    @DisplayName("SquadMatch 저장 테스트")
    void saveTest() {
        // given
        Players players = PlayerGenerator.generatePlayers(4);
        Squad squad = SquadGenerator.generate(players);
        Match match = MatchGenerator.generateMatch();
        em.persist(squad);
        em.persist(match);
        SquadMatchResult squadMatchResult = SquadMatchResultGenerator.generate(squad, match);

        // when
        squadMatchRepository.save(squadMatchResult);
        SquadMatchResult found = squadMatchRepository.findBy(squad, match);

        // then
        assertThat(found.getMatch().getMatchId()).isEqualTo(match.getMatchId());
        assertThat(found.getSquad().getSquadId()).isEqualTo(squad.getSquadId());
    }

    @Test
    @DisplayName("동일한 엔티티가 저장될 시, 예외 발생")
    void exceptionIfSaveAgain() {
        // given
        Players players = PlayerGenerator.generatePlayers(4);
        Squad squad = SquadGenerator.generate(players);
        Match match = MatchGenerator.generateMatch();
        em.persist(squad);
        em.persist(match);
        SquadMatchResult squadMatchResult = SquadMatchResultGenerator.generate(squad, match);
        squadMatchRepository.save(squadMatchResult);

        // when & then
        SquadMatchResult sameEntity = SquadMatchResultGenerator.generate(squad, match);
        assertThrows(
                DataIntegrityViolationException.class,
                () -> squadMatchRepository.save(sameEntity)
        ).printStackTrace();

    }
}
