package zoo.pubg.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.stereotype.Repository;
import zoo.pubg.domain.Player;
import zoo.pubg.domain.rank.Rank;
import zoo.pubg.domain.rank.Season;

@Repository
public class RankRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Rank> findAll(Player player) {
        return em.createQuery(
                        "SELECT r FROM Rank r WHERE r.player = :player", Rank.class
                ).setParameter("player", player)
                .getResultList();
    }

    public Rank findBy(Player player, Season season) {
        List<Rank> resultList = em.createQuery(
                        "SELECT r FROM Rank r WHERE r.player = :player AND r.season = :season", Rank.class
                ).setParameter("player", player)
                .setParameter("season", season)
                .getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        return resultList.get(0);
    }

    public void save(Rank rank) {
        Rank origin = findBy(rank.getPlayer(), rank.getSeason());
        if (origin == null) {
            em.persist(rank);
            return;
        }
        origin.update(rank);
    }
}
