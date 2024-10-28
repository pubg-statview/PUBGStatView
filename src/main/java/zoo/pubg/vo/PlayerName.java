package zoo.pubg.vo;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public final class PlayerName {

    private final static int MINIMUM_LENGTH = 4;
    private final static int MAXIMUM_LENGTH = 16;

    private String name;


    public PlayerName(String name) {
        valid(name);
        this.name = name;
    }

    private static void valid(String name) {
        if (name.matches("^account\\.[a-f0-9]*$") || name.matches("^ai\\.[a-f0-9]*")) {
            return;
        }
        if (!name.matches("^[a-zA-Z0-9][a-zA-Z0-9_-]*$") || name.length() < MINIMUM_LENGTH
                || name.length() > MAXIMUM_LENGTH) {
            throw new IllegalArgumentException("허용되지 않는 형식, name: " + name);
        }
    }
}
