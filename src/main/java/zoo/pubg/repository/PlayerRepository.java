package zoo.pubg.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.stereotype.Repository;
import zoo.pubg.domain.Player;
import zoo.pubg.vo.PlayerId;
import zoo.pubg.vo.PlayerName;


@Repository
public class PlayerRepository {

    @PersistenceContext
    private EntityManager em;

    public boolean isExists(Player player) {
        return find(player.getPlayerId()) != null;
    }

    public boolean isExistsId(PlayerId id) {
        return find(id) != null;
    }

    public Player find(PlayerId playerId) {
        return em.find(Player.class, playerId);
    }

    public Player findByName(PlayerName name) {
        List<Player> result = em.createQuery("select p from Player p " +
                        "where p.name = :name", Player.class)
                .setParameter("name", name)
                .getResultList();
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
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
        Player found = findByName(player.getName());
        if (found == null) {
            return;
        }
        if (!found.getPlayerId().equals(player.getPlayerId())) {
            PlayerName replacedName = new PlayerName(found.getPlayerId().getPlayerId());
            found.updateName(replacedName);
            em.flush();
        }
    }
}
