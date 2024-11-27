package zoo.pubg.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.stereotype.Repository;
import zoo.pubg.domain.Player;
import zoo.pubg.domain.Squad;
import zoo.pubg.domain.SquadPlayer;

@Repository
public class SquadPlayerRepository {

    @PersistenceContext
    private EntityManager em;

    public List<SquadPlayer> findBy(Player player) {
        return em.createQuery("SELECT sp from SquadPlayer sp WHERE player = :player", SquadPlayer.class)
                .setParameter("player", player)
                .getResultList();
    }

    public List<SquadPlayer> findBy(Squad squad) {
        return em.createQuery("SELECT sp from SquadPlayer sp WHERE squad = :squad", SquadPlayer.class)
                .setParameter("squad", squad)
                .getResultList();
    }

    public void save(SquadPlayer squadPlayer) {
        em.merge(squadPlayer);
    }
}
