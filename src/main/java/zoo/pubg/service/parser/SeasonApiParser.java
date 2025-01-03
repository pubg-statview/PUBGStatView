package zoo.pubg.service.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import zoo.pubg.service.parser.deserialization.season.SeasonDeserializer;

public class SeasonApiParser extends ApiParser<SeasonDeserializer> {

    @Override
    public SeasonDeserializer parse(String responseText) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        return mapper.readValue(responseText, SeasonDeserializer.class);
    }
}
