package zoo.pubg.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import zoo.pubg.domain.SquadPlayer;

@Repository
public class SquadPlayerRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(SquadPlayer squadPlayer) {
        em.persist(squadPlayer);
    }
}
