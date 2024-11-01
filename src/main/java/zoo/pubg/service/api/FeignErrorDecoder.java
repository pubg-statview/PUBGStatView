package zoo.pubg.service.api;

import feign.Response;
import feign.codec.ErrorDecoder;
import zoo.pubg.exception.NotFoundException;
import zoo.pubg.exception.TooManyRequestsException;

public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        int status = response.status();
        String message = "api" + status + "error";
        if (status == 404) {
            return new NotFoundException(message);
        }
        if (status == 429) {
            return new TooManyRequestsException(message);
        }
        return new IllegalArgumentException(message);
    }
}
