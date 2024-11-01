package zoo.pubg.service.parser.deserialization.custom;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import zoo.pubg.vo.PlayerName;

public class PlayerNameDeserializer extends JsonDeserializer<PlayerName> {
    @Override
    public PlayerName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode node = p.getCodec().readTree(p);
        String playerId = node.get("playerId").asText();
        JsonNode nameNode = node.get("name");
        if (nameNode == null || nameNode.asText() == null || nameNode.isEmpty()) {
            return new PlayerName(playerId);
        }
        return new PlayerName(nameNode.asText());
    }
}
