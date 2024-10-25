package zoo.pubg.service.parser.deserialization.match.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.ZonedDateTime;

public record MatchAttributes(String mapName, String gameMode, String matchType,
                              String shardId, int duration,
                              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
                              ZonedDateTime createdAt) {

}
