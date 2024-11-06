package zoo.pubg.service.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import zoo.pubg.service.parser.deserialization.season.SeasonDeserializer;
import zoo.pubg.service.parser.deserialization.season.data.SeasonData;

class SeasonApiParserTest {

    private final static String json = """
            {
              "data": [
                {
                  "type": "season",
                  "id": "division.bro.official.pc-2018-33",
                  "attributes": {
                    "isOffseason": false,
                    "isCurrentSeason": false
                  }
                },
                {
                  "type": "season",
                  "id": "division.bro.official.pc-2018-32",
                  "attributes": {
                    "isCurrentSeason": true,
                    "isOffseason": false
                  }
                },
                {
                  "type": "season",
                  "id": "division.bro.official.pc-2018-31",
                  "attributes": {
                    "isCurrentSeason": false,
                    "isOffseason": false
                  }
                },
                {
                  "type": "season",
                  "id": "division.bro.official.pc-2018-30",
                  "attributes": {
                    "isOffseason": false,
                    "isCurrentSeason": false
                  }
                }
              ],
              "links": {
                "self": "https://api.pubg.com/shards/kakao/seasons"
              },
              "meta": {}
            }
            """;

    private final static SeasonApiParser parser = new SeasonApiParser();

    @Test
    @DisplayName("Season API parse 테스트")
    void seasonParserTest() throws JsonProcessingException {
        // when
        SeasonDeserializer deserializer = parser.parse(json);
        List<SeasonData> data = deserializer.data();
        for (SeasonData d : data) {
            System.out.println(d.id().getId());
        }

        // then

    }
}