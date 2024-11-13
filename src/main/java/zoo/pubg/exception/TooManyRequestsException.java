package zoo.pubg.exception;

public class TooManyRequestsException extends RuntimeException {

    private static final String ERROR_PREFIX = "[Too Many Requests Exception] ";

    public TooManyRequestsException() {
        super(ERROR_PREFIX);
    }

    public TooManyRequestsException(String message) {
        super(ERROR_PREFIX + message);
    }
}