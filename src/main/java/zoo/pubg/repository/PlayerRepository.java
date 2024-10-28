package zoo.pubg.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import zoo.pubg.domain.Player;
import zoo.pubg.vo.PlayerName;


@Repository
public class PlayerRepository {

    @PersistenceContext
    private EntityManager em;

    public boolean isExists(Player player) {
        return find(player.getPlayerId()) != null;
    }

    public boolean isExistsId(String id) {
        return find(id) != null;
    }

    public Player find(String playerId) {
        return em.find(Player.class, playerId);
    }

    public Player findByName(PlayerName name) {
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
                PlayerName replacedName = new PlayerName(found.getPlayerId());
                found.updateName(replacedName);
            }
        } catch (NoResultException ignored) {

        }
    }
}
