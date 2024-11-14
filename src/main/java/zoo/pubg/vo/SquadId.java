package zoo.pubg.vo;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SquadId implements Serializable {

    private String squadId;

    public static SquadId from(String... ids) {
        String join = String.join(",", ids);
        UUID uuid = UUID.nameUUIDFromBytes(join.getBytes(StandardCharsets.UTF_8));
        return new SquadId(uuid.toString());
    }

    public static SquadId from(List<String> ids) {
        return from(ids.toArray(String[]::new));
    }

    public static SquadId from(PlayerIds ids) {
        String join = ids.joinToString();
        UUID uuid = UUID.nameUUIDFromBytes(join.getBytes(StandardCharsets.UTF_8));
        return new SquadId(uuid.toString());
    }
}
