package zoo.pubg.service.dto.match.included;

import lombok.Getter;
import lombok.Setter;
import zoo.pubg.service.dto.match.included.asset.AssetAttributes;

@Getter
@Setter
public class Asset extends Included {

    private AssetAttributes attributes;

    public String getTelemetryUrl() {
        return attributes.URL();
    }
}
