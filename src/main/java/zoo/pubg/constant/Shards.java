package zoo.pubg.constant;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Shards {
    STEAM("steam"),
    KAKAO("kakao"),
    PSN("psn"),
    STADIA("stadia"),
    XBOX("xbox"),
    kakao("kakao");

    private final String shardName;

    Shards(String shardName) {
        this.shardName = shardName;
    }

    public String getShardName() {
        return this.shardName;
    }

    @JsonCreator
    public static Shards of(String shards) {
        try {
            return valueOf(shards.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("잘못된 지역");
        }
    }
}
