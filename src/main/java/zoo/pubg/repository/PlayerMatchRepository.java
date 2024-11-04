package zoo.pubg.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import zoo.pubg.domain.PlayerMatchResult;
import zoo.pubg.domain.Player;

import java.util.List;

@Repository
public class PlayerMatchRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(PlayerMatchResult playerMatchResult) {
        em.persist(playerMatchResult);
    }

    public Page<PlayerMatchResult> findAllWithPagination(Player player, int page, int size) {
        List<PlayerMatchResult> results = em.createQuery("SELECT pm FROM PlayerMatchResult pm WHERE pm.player = :player", PlayerMatchResult.class)
                .setParameter("player", player)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
        long totalResults = countPlayerMatchResultByPlayer(player);
        Pageable pageable = PageRequest.of(page, size);

        return new PageImpl<>(results, pageable, totalResults);
    }

    private long countPlayerMatchResultByPlayer(Player player) {
        return em.createQuery("SELECT COUNT(pm) FROM PlayerMatchResult pm WHERE pm.player = :player", Long.class)
                .setParameter("player", player)
                .getSingleResult();
    }
}
