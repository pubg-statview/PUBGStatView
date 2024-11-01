package zoo.pubg.service.parser.deserialization.match.included.asset;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.ZonedDateTime;

public record AssetAttributes(String URL,
                              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
                              ZonedDateTime createdAt) {

}
