package zoo.pubg.vo;

import jakarta.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class RosterId implements Serializable {

    @Serial
    private static final long serialVersionUID = -2620056007464296518L;

    private String rosterId;
}
