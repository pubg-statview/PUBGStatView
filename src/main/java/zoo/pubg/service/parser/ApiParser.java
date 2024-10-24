package zoo.pubg.service.parser;

import com.fasterxml.jackson.core.JsonProcessingException;

public abstract class ApiParser<R> {

    public abstract R parse(String responseText) throws JsonProcessingException;
}
