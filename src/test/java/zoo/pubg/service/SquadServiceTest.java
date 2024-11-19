package zoo.pubg.service;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.domain.Squad;
import zoo.pubg.domain.list.Players;
import zoo.pubg.factory.PlayerGenerator;
import zoo.pubg.factory.SquadGenerator;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class SquadServiceTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private SquadService squadService;

    @Test
    @DisplayName("저장된 스쿼드가 잘 찾아지는 지 테스트")
    void searchSquadTest() {
        // given
        Players players = PlayerGenerator.generatePlayers(4);
        players.forEach(player -> em.persist(player));
        Squad squad = SquadGenerator.generate(players);
        em.persist(squad);

        // when
        Squad result = squadService.searchSquad(players.getPlayerNames());

        // then
        assertThat(result.getSquadId()).isEqualTo(squad.getSquadId());

    }
}