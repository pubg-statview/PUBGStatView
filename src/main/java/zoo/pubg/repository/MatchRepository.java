package zoo.pubg.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import zoo.pubg.domain.Match;

@Repository
public class MatchRepository {

    @PersistenceContext
    private EntityManager em;

    public boolean isExists(String id) {
        return find(id) != null;
    }

    public Match find(String id) {
        return em.find(Match.class, id);
    }

    public void save(Match match) {
        em.persist(match);
    }
}
