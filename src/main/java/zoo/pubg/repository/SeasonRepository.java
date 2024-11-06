package zoo.pubg.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import zoo.pubg.domain.rank.Season;
import zoo.pubg.vo.SeasonId;

@Repository
public class SeasonRepository {

    @PersistenceContext
    private EntityManager em;

    public boolean isExists(SeasonId id) {
        return find(id) != null;
    }

    public Season find(SeasonId id) {
        return em.find(Season.class, id);
    }

    public void save(Season season) {
        Season origin = find(season.getId());
        if (origin != null) {
            origin.update(season);
            return;
        }
        em.persist(season);
    }
}
