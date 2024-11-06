package zoo.pubg.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.stereotype.Repository;
import zoo.pubg.constant.Shards;
import zoo.pubg.domain.rank.Season;
import zoo.pubg.vo.SeasonId;

@Repository
public class SeasonRepository {

    @PersistenceContext
    private EntityManager em;

    public Season find(SeasonId id, Shards shards) {
        return em.find(Season.class, id);
    }

    public Season findCurrentSeason() {
        return em.createQuery(
                "SELECT s FROM Season s WHERE isCurrentSeason = true", Season.class
        ).getSingleResult();
    }

    public boolean isUpdateNeeded(Season currentSeason) {
        List<SeasonId> results = em.createQuery(
                        "SELECT s.seasonId FROM Season s WHERE s.shards = :shards and isCurrentSeason = true",
                        SeasonId.class
                ).setParameter("shards", currentSeason.getShards())
                .getResultList();
        return results.isEmpty() || !currentSeason.getSeasonId().equals(results.get(0));
    }

    public void save(Season season) {
        Season origin = find(season.getSeasonId(), season.getShards());
        if (origin != null) {
            origin.update(season);
            return;
        }
        em.persist(season);
    }
}
