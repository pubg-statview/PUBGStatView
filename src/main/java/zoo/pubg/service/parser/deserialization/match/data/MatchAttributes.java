package zoo.pubg.service.parser.deserialization.match.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.ZonedDateTime;
import zoo.pubg.constant.Shards;

public record MatchAttributes(String mapName, String gameMode, String matchType,
                              Shards shardId, int duration,
                              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
                              ZonedDateTime createdAt) {

}
