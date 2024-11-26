package zoo.pubg.constant;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public enum LastUpdated {
    INITIALIZATION(LocalDateTime.of(2000, 1, 1, 0, 0, 0, 0));

    private final LocalDateTime time;

    LastUpdated(LocalDateTime time) {
        this.time = time;
    }
}
