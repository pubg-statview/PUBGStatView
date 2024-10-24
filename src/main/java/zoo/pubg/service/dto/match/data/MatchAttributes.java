package zoo.pubg.service.dto.match.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.ZonedDateTime;
import lombok.Getter;

@Getter
public class MatchAttributes {

    private String mapName;
    private String gameMode;
    private String matchType;
    private int duration;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    ZonedDateTime createdAt;
}
