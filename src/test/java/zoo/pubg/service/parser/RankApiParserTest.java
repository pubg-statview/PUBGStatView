package zoo.pubg.service.parser;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import zoo.pubg.service.parser.deserialization.rank.RankDeserializer;

class RankApiParserTest {

    private final static RankApiParser parser = new RankApiParser();

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

    @Test
    @DisplayName("rank parser test - 값이 있을 때")
    void parserTest() throws JsonProcessingException {
        // given
        String json = jsonFormat.formatted("""
                "squad-fpp": {
                    "currentTier": {"tier": "Gold","subTier": "5"},"currentRankPoint": 2086,"bestTier": {"tier": "Gold","subTier": "5"},"bestRankPoint": 2098,"roundsPlayed": 34,
                    "avgRank": 9.382353,"avgSurvivalTime": 0,"top10Ratio": 0.6764706,"winRatio": 0,"assists": 9,"wins": 0,"kda": 0.7941176,"kdr": 0,"kills": 18,"deaths": 34,"roundMostKills": 0,"longestKill": 0,"headshotKills": 0,"headshotKillRatio": 0,"damageDealt": 5680.191,"dBNOs": 29,"reviveRatio": 0,"revives": 0,"heals": 0,"boosts": 0,"weaponsAcquired": 0,"teamKills": 0,"playTime": 0,"killStreak": 0
                }
                """);

        // when
        RankDeserializer deserializer = parser.parse(json);

        // then
        System.out.println(deserializer);
    }

    @Test
    @DisplayName("rank parser test - 값이 없을 때")
    void parserTestNull() throws JsonProcessingException {
        // given
        String json = jsonFormat.formatted("");

        // when
        RankDeserializer deserializer = parser.parse(json);

        // then
        assertThat(deserializer).isEqualTo(null);

    }
}