package ulkhnv.dao;

import org.springframework.stereotype.Repository;
import ulkhnv.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void addUser(User user){
        entityManager.persist(user);
    }

    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u JOIN FETCH u.role", User.class).getResultList();
    }

    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    public void updateUser(User user) {
        entityManager.merge(user);
    }

    public void removeUser(Long id) {
        entityManager.remove(getUserById(id));
    }

    public User getUserByUsername(String username) {
        try {
            return entityManager.createQuery("SELECT u FROM User u JOIN FETCH u.role WHERE u.username =:username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isExists(String username) {
        return entityManager.createQuery("SELECT COUNT(u.id) FROM User u WHERE u.username =: username",Long.class)
                .setParameter("username",username).getSingleResult() > 0;
    }
}
