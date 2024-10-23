package zoo.pubg.service.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import zoo.pubg.service.dto.player.PlayerDto;

public class PlayerApiParser extends ApiParser<PlayerDto> {

    @Override
    public PlayerDto parse(String responseText) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(responseText, PlayerDto.class);
    }
}
