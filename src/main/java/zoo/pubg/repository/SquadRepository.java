package zoo.pubg.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import zoo.pubg.domain.Squad;
import zoo.pubg.vo.SquadId;

@Repository
public class SquadRepository {

    @PersistenceContext
    private EntityManager em;

    public Squad find(SquadId squadId) {
        return em.find(Squad.class, squadId);
    }

    public void save(Squad squad) {
        em.persist(squad);
    }
}
