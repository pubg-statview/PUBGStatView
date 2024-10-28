package zoo.pubg.constant;

public enum Shards {
    STEAM("steam"),
    KAKAO("kakao"),
    PSN("psn"),
    STADIA("stadia"),
    XBOX("xbox");

    private final String shardName;

    Shards(String shardName) {
        this.shardName = shardName;
    }

    public String getShardName() {
        return this.shardName;
    }

    public static Shards of(String shards) {
        try {
            return valueOf(shards.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("잘못된 지역");
        }
    }
}
