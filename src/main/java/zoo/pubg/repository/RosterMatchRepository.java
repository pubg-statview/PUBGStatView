package zoo.pubg.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import zoo.pubg.domain.RosterMatchResult;

@Repository
public class RosterMatchRepository {


    @PersistenceContext
    private EntityManager em;

    public void save(RosterMatchResult rosterMatchResult) {
        em.persist(rosterMatchResult);
    }
}
