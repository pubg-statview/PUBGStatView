package zoo.pubg.constant;

public enum Shards {
    STEAM, KAKAO;

    public static Shards of(String shards) {
        try {
            return valueOf(shards.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("잘못된 지역");
        }
    }
}
