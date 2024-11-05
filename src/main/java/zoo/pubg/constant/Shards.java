package zoo.pubg.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
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

    @JsonCreator
    public static Shards of(String shards) {
        try {
            return valueOf(shards.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("잘못된 지역");
        }
    }
}
