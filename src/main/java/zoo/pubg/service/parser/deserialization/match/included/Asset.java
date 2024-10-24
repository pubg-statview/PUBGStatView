package zoo.pubg.service.parser.deserialization.match.included;

import lombok.Getter;
import lombok.Setter;
import zoo.pubg.service.dto.AssetDto;
import zoo.pubg.service.parser.deserialization.match.included.asset.AssetAttributes;

@Getter
@Setter
public class Asset extends Included {

    private AssetAttributes attributes;

    public AssetDto toDto() {
        return new AssetDto(attributes.URL());
    }
}
