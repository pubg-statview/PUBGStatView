package zoo.pubg.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import zoo.pubg.domain.Match;
import zoo.pubg.domain.Player;
import zoo.pubg.domain.PlayerMatchResult;
import zoo.pubg.domain.list.Players;

@Repository
public class PlayerMatchRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(PlayerMatchResult playerMatchResult) {
        em.persist(playerMatchResult);
    }

    public List<PlayerMatchResult> findAllParticipants(Match match) {
        return em.createQuery(
                        "SELECT pm FROM PlayerMatchResult pm WHERE pm.match = :match", PlayerMatchResult.class
                ).setParameter("match", match)
                .getResultList();
    }

    public Page<PlayerMatchResult> findAllWithPagination(Player player, int page, int size) {
        List<PlayerMatchResult> results = em.createQuery(
                        """
                                SELECT pm FROM PlayerMatchResult pm
                                JOIN pm.match m
                                WHERE pm.player = :player
                                ORDER BY m.createdAt DESC""", PlayerMatchResult.class)
                .setParameter("player", player)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
        long totalResults = countPlayerMatchResultByPlayer(player);
        Pageable pageable = PageRequest.of(page, size);

        return new PageImpl<>(results, pageable, totalResults);
    }

    public List<PlayerMatchResult> findByPlayersInSameRoster(Players players, LocalDateTime lastUpdated) {
        return em.createQuery("""
                        SELECT pmr FROM PlayerMatchResult pmr
                        WHERE pmr.rosterMatchResult IN (
                            SELECT pmr2.rosterMatchResult FROM PlayerMatchResult pmr2
                            WHERE pmr2.player IN :players
                            GROUP BY pmr2.rosterMatchResult
                            HAVING COUNT(pmr2.player) = :playerCount
                        ) AND pmr.match.createdAt > :lastUpdated
                        """, PlayerMatchResult.class
                ).setParameter("players", players.getList())
                .setParameter("playerCount", players.size())
                .setParameter("lastUpdated", lastUpdated)
                .getResultList();
    }

    private long countPlayerMatchResultByPlayer(Player player) {
        return em.createQuery("SELECT COUNT(pm) FROM PlayerMatchResult pm WHERE pm.player = :player", Long.class)
                .setParameter("player", player)
                .getSingleResult();
    }
}
