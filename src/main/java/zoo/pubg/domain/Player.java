package zoo.pubg.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;

@Entity
@Getter
public class Player {

    @Id
    private String playerId;

    private String name;
    private String shardId;
    private String clanId;
    private LocalDateTime lastUpdated;

}
