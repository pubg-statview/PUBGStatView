package zoo.pubg.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.constant.Shards;
import zoo.pubg.domain.Squad;
import zoo.pubg.domain.SquadPlayer;
import zoo.pubg.domain.list.Players;
import zoo.pubg.factory.PlayerGenerator;
import zoo.pubg.factory.SquadGenerator;
import zoo.pubg.repository.SquadPlayerRepository;
import zoo.pubg.vo.SquadId;
import zoo.pubg.vo.list.PlayerNames;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class SquadServiceTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private SquadPlayerRepository squadPlayerRepository;

    @Autowired
    private SquadService squadService;

    @MockBean
    private PlayerIntegrationService playerIntegrationService;

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

    @Test
    @DisplayName("스쿼드 저장 테스트")
    void fetchSquadTest() throws JsonProcessingException {
        // given
        Players players = PlayerGenerator.generatePlayers(4);
        Shards shards = Shards.KAKAO;
        PlayerNames playerNames = players.getPlayerNames();

        // when
        when(playerIntegrationService.fetchPlayers(shards, playerNames))
                .thenReturn(players);
        players.forEach(player -> em.persist(player));

        squadService.fetchSquad(shards, playerNames);

        // then
        SquadId squadId = SquadId.from(players.getPlayerIds());
        Squad squad = squadService.searchSquad(playerNames);

        assertThat(squadId).isEqualTo(squad.getSquadId());

        List<SquadPlayer> squadPlayers = squadPlayerRepository.findBy(squad);
        assertThat(squadPlayers).hasSize(4);
    }
}