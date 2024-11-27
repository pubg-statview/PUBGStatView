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
        Player foundById = find(player.getPlayerId());
        Player foundByName = findByName(player.getName());

        if (foundById == null && foundByName == null) {
            em.persist(player);
            return;
        }

        if (foundByName != null && !player.hasSameId(foundByName)) {
            PlayerName newName = new PlayerName(foundByName.getPlayerId().getPlayerId());
            foundByName.updateName(newName);
        }

        if (foundById == null) {
            em.persist(player);
        } else {
            foundById.updateAll(player);
        }
    }
}
