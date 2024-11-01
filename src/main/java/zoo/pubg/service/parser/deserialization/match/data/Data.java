package zoo.pubg.service.parser.deserialization.match.data;

import lombok.Getter;
import zoo.pubg.service.dto.MatchDataDto;

@Getter
public class Data {

    private String id;
    private MatchAttributes attributes;

    public MatchDataDto toDto() {
        return new MatchDataDto(
                id, attributes.mapName(), attributes.gameMode(), attributes.matchType(), attributes.shardId(),
                attributes.duration(), attributes.createdAt().toLocalDateTime()
        );
    }
}
