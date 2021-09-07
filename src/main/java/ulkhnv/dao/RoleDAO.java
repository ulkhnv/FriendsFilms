package ulkhnv.dao;

import org.springframework.stereotype.Repository;
import ulkhnv.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void addRole(Role role) {
        entityManager.persist(role);
    }

    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }

    public Role getRoleByName(String name) {
        return entityManager.createQuery("SELECT r FROM Role r WHERE r.name =:n", Role.class)
                .setParameter("n", name)
                .getSingleResult();
    }

    public void removeRole(Long id) {
        entityManager.remove(getRoleById(id));
    }

    public List<Role> getAllRoles() {
        return entityManager.createQuery("SELECT r FROM Role r", Role.class).getResultList();
    }
}