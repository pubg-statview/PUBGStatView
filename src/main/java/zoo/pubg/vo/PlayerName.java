package zoo.pubg.vo;

public record PlayerName(String name) {

    private final static int MINIMUM_LENGTH = 4;
    private final static int MAXIMUM_LENGTH = 16;

    public PlayerName {
        valid(name);
    }

    private static void valid(String name) {
        if (!name.matches("^[a-zA-Z0-9][a-zA-Z0-9_-]*$") || name.length() < MINIMUM_LENGTH
                || name.length() > MAXIMUM_LENGTH) {
            throw new IllegalArgumentException("허용되지 않는 형식");
        }
    }
}
