package zoo.pubg.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class SquadMatchServiceTest {

    @Autowired
    private SquadMatchService squadMatchService;

    @Test
    @DisplayName("스쿼드 매치 결과 패치 테스트")
    void fetchSquadMatchTest() {
        // given

        // when

        // then

    }
}