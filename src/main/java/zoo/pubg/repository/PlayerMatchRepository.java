package zoo.pubg.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import zoo.pubg.domain.PlayerMatchResult;

@Repository
public class PlayerMatchRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(PlayerMatchResult playerMatchResult) {
        em.persist(playerMatchResult);
    }
}
