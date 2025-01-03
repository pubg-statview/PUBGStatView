package zoo.pubg.service.parser.deserialization.match.included;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(
        use = Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Participant.class, name = "participant"),
        @JsonSubTypes.Type(value = Roster.class, name = "roster"),
        @JsonSubTypes.Type(value = Asset.class, name = "asset")
})
public abstract class Included {
}
