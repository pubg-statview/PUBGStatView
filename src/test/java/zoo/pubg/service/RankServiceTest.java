package zoo.pubg.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import zoo.pubg.constant.PlayerType;
import zoo.pubg.constant.Shards;
import zoo.pubg.domain.Player;
import zoo.pubg.domain.rank.Rank;
import zoo.pubg.domain.rank.Season;
import zoo.pubg.repository.RankRepository;
import zoo.pubg.service.api.PubgBasicApi;
import zoo.pubg.vo.PlayerId;
import zoo.pubg.vo.PlayerName;
import zoo.pubg.vo.SeasonId;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class RankServiceTest {

    private final static String jsonFormat = """
            {
              "data": {
                "type": "rankedplayerstats",
                "attributes": {
                  "rankedGameModeStats": {
                    %s
                  }
                }
              }
            }
            """;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private RankService rankService;

    @Autowired
    private RankRepository rankRepository;

    @MockBean
    private PubgBasicApi pubgBasicApi;

    @Test
    @DisplayName("rank service test")
    void test() throws JsonProcessingException {
        // given
        Shards shards = Shards.KAKAO;
        Player player = new Player(
                new PlayerId("account.test1234"), new PlayerName("testPlayer"), shards,
                "clan.testClasn", PlayerType.ETC, LocalDateTime.now()
        );
        Season season = new Season(new SeasonId("currentSeason"), true, shards);

        em.persist(player);
        em.persist(season);

        String mockApiResponse = jsonFormat.formatted("""
                "squad-fpp": {
                    "currentTier": {"tier": "Gold","subTier": "5"},"currentRankPoint": 2086,"bestTier": {"tier": "Gold","subTier": "5"},"bestRankPoint": 2098,"roundsPlayed": 34,
                    "avgRank": 9.382353,"avgSurvivalTime": 0,"top10Ratio": 0.6764706,"winRatio": 0,"assists": 9,"wins": 0,"kda": 0.7941176,"kdr": 0,"kills": 18,"deaths": 34,"roundMostKills": 0,"longestKill": 0,"headshotKills": 0,"headshotKillRatio": 0,"damageDealt": 5680.191,"dBNOs": 29,"reviveRatio": 0,"revives": 0,"heals": 0,"boosts": 0,"weaponsAcquired": 0,"teamKills": 0,"playTime": 0,"killStreak": 0
                },
                "squad": {
                    "currentTier": {"tier": "Gold","subTier": "5"},"currentRankPoint": 2086,"bestTier": {"tier": "Gold","subTier": "5"},"bestRankPoint": 2098,"roundsPlayed": 34,
                    "avgRank": 9.382353,"avgSurvivalTime": 0,"top10Ratio": 0.6764706,"winRatio": 0,"assists": 9,"wins": 0,"kda": 0.7941176,"kdr": 0,"kills": 18,"deaths": 34,"roundMostKills": 0,"longestKill": 0,"headshotKills": 0,"headshotKillRatio": 0,"damageDealt": 5680.191,"dBNOs": 29,"reviveRatio": 0,"revives": 0,"heals": 0,"boosts": 0,"weaponsAcquired": 0,"teamKills": 0,"playTime": 0,"killStreak": 0
                }
                """);

        when(pubgBasicApi.fetchRank(shards.getShardName(), player.getPlayerId().getPlayerId(),
                season.getSeasonId().getId())).thenReturn(mockApiResponse);

        // when
        rankService.fetch(shards, player, season);

        // then
        List<Rank> ranks = rankRepository.findAll(player);
        assertThat(ranks.size()).isEqualTo(2);
        assertThat(ranks.get(0).getRankedDetails().getTier().getCurrentTier()).isEqualTo("Gold");
    }
}