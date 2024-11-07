package zoo.pubg.service.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import zoo.pubg.service.parser.deserialization.rank.RankDeserializer;

class RankApiParserTest {

    private final static RankApiParser parser = new RankApiParser();

    private final static String json = """
            {
              "data": {
                "type": "rankedplayerstats",
                "attributes": {
                  "rankedGameModeStats": {
                    "squad": {
                      "currentTier": {
                        "tier": "Gold",
                        "subTier": "5"
                      },
                      "currentRankPoint": 2086,
                      "bestTier": {
                        "tier": "Gold",
                        "subTier": "5"
                      },
                      "bestRankPoint": 2098,
                      "roundsPlayed": 34,
                      "avgRank": 9.382353,
                      "avgSurvivalTime": 0,
                      "top10Ratio": 0.6764706,
                      "winRatio": 0,
                      "assists": 9,
                      "wins": 0,
                      "kda": 0.7941176,
                      "kdr": 0,
                      "kills": 18,
                      "deaths": 34,
                      "roundMostKills": 0,
                      "longestKill": 0,
                      "headshotKills": 0,
                      "headshotKillRatio": 0,
                      "damageDealt": 5680.191,
                      "dBNOs": 29,
                      "reviveRatio": 0,
                      "revives": 0,
                      "heals": 0,
                      "boosts": 0,
                      "weaponsAcquired": 0,
                      "teamKills": 0,
                      "playTime": 0,
                      "killStreak": 0
                    }
                  }
                },
                "relationships": {
                  "player": {
                    "data": {
                      "type": "player",
                      "id": "account.009bfb0aad7d4238a1690939feb5f5c7"
                    }
                  },
                  "season": {
                    "data": {
                      "type": "season",
                      "id": "division.bro.official.pc-2018-32"
                    }
                  }
                }
              },
              "links": {
                "self": "https://api.pubg.com/shards/kakao/players/account.009bfb0aad7d4238a1690939feb5f5c7/seasons/division.bro.official.pc-2018-32/ranked"
              },
              "meta": {}
            }
            """;

    @Test
    @DisplayName("rank api test")
    void test() throws JsonProcessingException {
        // given
        RankDeserializer parse = parser.parse(json);
        System.out.println(parse.data());

        // when

        // then

    }
}