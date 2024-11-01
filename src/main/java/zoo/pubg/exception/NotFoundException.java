package zoo.pubg.exception;

public class NotFoundException extends RuntimeException {

    private static final String ERROR_PREFIX = "[Not Found Exception] ";

    public NotFoundException() {
        super(ERROR_PREFIX);
    }

    public NotFoundException(String message) {
        super(ERROR_PREFIX + message);
    }
}
