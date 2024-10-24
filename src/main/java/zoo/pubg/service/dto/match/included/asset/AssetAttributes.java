package zoo.pubg.service.dto.match.included.asset;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.ZonedDateTime;

public class AssetAttributes {

    private String URL;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private ZonedDateTime createdAt;
}
