package zoo.pubg.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import zoo.pubg.domain.Match;
import zoo.pubg.domain.Squad;
import zoo.pubg.domain.SquadMatchResult;

@Repository
public class SquadMatchRepository {

    @PersistenceContext
    private EntityManager em;

    public List<SquadMatchResult> findBy(Squad squad) {
        return em.createQuery("SELECT sm FROM SquadMatchResult sm WHERE squad = :squad", SquadMatchResult.class)
                .setParameter("squad", squad)
                .getResultList();
    }

    public List<SquadMatchResult> findBy(Match match) {
        return em.createQuery("SELECT sm FROM SquadMatchResult sm WHERE match = :match", SquadMatchResult.class)
                .setParameter("match", match)
                .getResultList();
    }

    public Page<SquadMatchResult> findAllWithPagination(Squad squad, int page, int size) {
        List<SquadMatchResult> results = em.createQuery(
                        """
                                SELECT smr FROM SquadMatchResult smr
                                WHERE smr.squad = :squad
                                ORDER BY smr.match.createdAt DESC""", SquadMatchResult.class
                ).setParameter("squad", squad)
                .getResultList();
        long totalResults = countSquadMatchResult(squad);
        Pageable pageable = PageRequest.of(page, size);

        return new PageImpl<>(results, pageable, totalResults);
    }

    public SquadMatchResult findBy(Squad squad, Match match) {
        List<SquadMatchResult> resultList = em.createQuery("SELECT sm FROM SquadMatchResult sm "
                        + "WHERE squad = :squad AND match = :match", SquadMatchResult.class)
                .setParameter("squad", squad)
                .setParameter("match", match)
                .getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        return resultList.get(0);
    }

    public void save(SquadMatchResult squadMatchResult) {
        em.persist(squadMatchResult);
    }

    private long countSquadMatchResult(Squad squad) {
        return em.createQuery("SELECT COUNT(smr) FROM SquadMatchResult smr WHERE smr.squad = :squad", Long.class)
                .setParameter("squad", squad)
                .getSingleResult();
    }
}
