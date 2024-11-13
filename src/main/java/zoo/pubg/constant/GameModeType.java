package zoo.pubg.constant;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum GameModeType {
    SQUAD("squad"),
    SQUAD_FPP("squad-fpp"),
    DUO("duo"),
    DUO_FPP("duo-fpp"),
    SOLO("solo"),
    SOLO_FPP("solo-fpp");

    private final String name;

    GameModeType(String name) {
        this.name = name;
    }

    public static GameModeType of(String name) {
        return Arrays.stream(values())
                .filter(type -> name.equals(type.name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 게임 모드"));
    }
}
