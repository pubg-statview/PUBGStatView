package zoo.pubg.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.stereotype.Repository;
import zoo.pubg.constant.Shards;
import zoo.pubg.domain.rank.Season;

@Repository
public class SeasonRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Season> findAll(Shards shards) {
        return em.createQuery(
                        "SELECT s FROM Season s WHERE s.shards = :shards", Season.class
                ).setParameter("shards", shards)
                .getResultList();
    }

    public Season findCurrentSeason(Shards shards) {
        List<Season> seasons = findAll(shards);
        return seasons.stream()
                .filter(Season::getIsCurrentSeason)
                .findAny()
                .orElse(null);
    }

    public void save(Season season) {
        em.persist(season);
    }
}
