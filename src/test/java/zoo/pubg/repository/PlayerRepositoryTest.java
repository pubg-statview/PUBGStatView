package zoo.pubg.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.domain.Player;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository repository;

    @Test
    @DisplayName("플레이어 이름으로 엔티티 검색")
    void findByNameTest() {
        String playerId = "12345678";
        String name = "playerName";
        // given
        Player player = new Player(
                playerId, name, "kakao", "clanId", LocalDateTime.now()
        );
        repository.save(player);

        // when
        Player result = repository.findByName(name);

        // then
        assertThat(player.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("저장하려는 이름이 이미 있지만 id가 서로 다르다면, 기존의 이름을 가진 player 이름 업데이트 후 저장")
    void duplicatedNameSave() {
        // given
        Player playerWhoChangedName = new Player(
                "playerId.aaa", "AAA", "kakao", "clanId.123", LocalDateTime.now()
        );
        repository.save(playerWhoChangedName);
        Player player = new Player(
                "playerId.bbb", "AAA", "kakao", "clanId.123", LocalDateTime.now()
        );

        // when
        repository.save(player);

        Player player1 = repository.find(playerWhoChangedName.getPlayerId());
        Player player2 = repository.find(player.getPlayerId());

        // then
        assertThat(player1.getName()).isEqualTo(playerWhoChangedName.getPlayerId());
        assertThat(player2.getName()).isEqualTo(player.getName());

    }

    @Test
    @DisplayName("저장하려는 이름이 있고, id가 같다면 현재 객체로 업데이트")
    void alreadyExistsId() {
        // given
        Player originalPlayer = new Player(
                "playerId.aaa", "AAA", "kakao", "clanId.123", LocalDateTime.now()
        );
        repository.save(originalPlayer);
        Player player = new Player(
                "playerId.aaa", "BBB", "kakao", "clanId.123", LocalDateTime.now()
        );

        // when
        repository.save(player);
        Player result = repository.find(player.getPlayerId());

        // then
        assertThat(result.getName()).isEqualTo(player.getName());
    }
}
