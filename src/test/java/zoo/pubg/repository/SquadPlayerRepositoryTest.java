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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.domain.Player;
import zoo.pubg.domain.Squad;
import zoo.pubg.domain.SquadPlayer;
import zoo.pubg.domain.list.Players;
import zoo.pubg.factory.PlayerGenerator;
import zoo.pubg.factory.SquadGenerator;
import zoo.pubg.vo.SquadPlayerId;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class SquadPlayerRepositoryTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private SquadPlayerRepository squadPlayerRepository;

    @Test
    @DisplayName("SquadPlayer 저장 및 조회 테스트")
    void saveTest() {
        // given
        Players players = PlayerGenerator.generatePlayers(4);
        Squad squad = SquadGenerator.generate(players);

        for (Player player : players) {
            em.persist(player);
        }
        em.persist(squad);

        // when
        for (Player player : players) {
            SquadPlayerId squadPlayerId = new SquadPlayerId(squad.getSquadId(), player.getPlayerId());
            SquadPlayer squadPlayer = new SquadPlayer(squad, player);
            System.out.println(squadPlayerId.getPlayerId());
            squadPlayerRepository.save(squadPlayer);
        }

        // then
        for (Player player : players) {
            List<SquadPlayer> by = squadPlayerRepository.findBy(player);
            assertThat(by).hasSize(1);
            SquadPlayer squadPlayer = by.get(0);
            SquadPlayerId squadPlayerId = new SquadPlayerId(squad.getSquadId(), player.getPlayerId());
            assertThat(squadPlayer.getSquadPlayerId()).isEqualTo(squadPlayerId);
        }

    }
}