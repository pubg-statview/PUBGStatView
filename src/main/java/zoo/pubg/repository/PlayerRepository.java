package zoo.pubg.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import zoo.pubg.domain.Player;


@Repository
public class PlayerRepository {

    @PersistenceContext
    private EntityManager em;

    public boolean isExists(Player player) {
        return find(player.getPlayerId()) != null;
    }

    public Player find(String playerId) {
        return em.find(Player.class, playerId);
    }

    public Player findByName(String name) {
        return em.createQuery("select p from Player p " +
                        "where p.name = :name", Player.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public void save(Player player) {
        valid(player);
        if (this.isExists(player)) {
            em.merge(player);
            return;
        }
        em.persist(player);
    }

    private void valid(Player player) {
        try {
            Player found = findByName(player.getName());
            if (!found.getPlayerId().equals(player.getPlayerId())) {
                found.updateName(found.getPlayerId());
                em.merge(found);
            }
        } catch (NoResultException ignored) {

        }
    }
}
