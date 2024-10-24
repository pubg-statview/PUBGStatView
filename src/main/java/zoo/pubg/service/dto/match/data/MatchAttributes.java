package zoo.pubg.service.dto.match.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.ZonedDateTime;

public record MatchAttributes(String mapName, String gameMode, String matchType,
                              int duration,
                              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
                              ZonedDateTime createdAt) {

}
