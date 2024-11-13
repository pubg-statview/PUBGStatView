package zoo.pubg.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.stereotype.Repository;
import zoo.pubg.constant.GameModeType;
import zoo.pubg.domain.Player;
import zoo.pubg.domain.rank.Rank;
import zoo.pubg.domain.rank.RankedDetails;
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

    public List<Rank> findBy(Player player, Season season) {
        return em.createQuery(
                        "SELECT r FROM Rank r WHERE r.player = :player AND r.season = :season", Rank.class
                ).setParameter("player", player)
                .setParameter("season", season)
                .getResultList();
    }

    public Rank find(Player player, Season season, GameModeType type) {
        List<Rank> resultList = em.createQuery(
                        "SELECT r FROM Rank r WHERE r.player = :player AND r.season = :season AND r.gameMode = :type",
                        Rank.class
                ).setParameter("player", player)
                .setParameter("season", season)
                .setParameter("type", type)
                .getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        return resultList.get(0);
    }

    public void save(Rank rank) {
        Rank origin = find(rank.getPlayer(), rank.getSeason(), rank.getGameMode());
        if (origin == null) {
            em.persist(rank);
            return;
        }
        RankedDetails rankedDetails = origin.getRankedDetails();
        if (rankedDetails != null) {
            em.remove(rankedDetails);
        }
        origin.update(rank);
    }
}
