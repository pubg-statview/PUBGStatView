package zoo.pubg.service.dto.match.included;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(
        use = Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Participant.class, name = "participant"),
        @JsonSubTypes.Type(value = Roster.class, name = "roster"),
        @JsonSubTypes.Type(value = Asset.class, name = "asset")
})
public abstract class Includes {
    protected String type;
}
