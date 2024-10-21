package zoo.pubg.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import zoo.pubg.domain.Player;


@Repository
public class PlayerRepository {

    @PersistenceContext
    private EntityManager em;

    public boolean isExists(Player player) {
        return em.contains(player);
    }

    public Player find(String playerId) {
        return em.find(Player.class, playerId);
    }

    public String save(Player player) {
        if (this.isExists(player)) {
            return player.getPlayerId();
        }
        em.persist(player);
        return player.getPlayerId();
    }

}
