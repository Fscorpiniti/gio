package ar.edu.untref.gio.persistence;

import ar.edu.untref.gio.domain.User;
import ar.edu.untref.gio.domain.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Repository("relationalUserRepository")
public class RelationalUserRepository implements UserRepository {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "gioPersistenceUnit")
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void add(User user) {
        entityManager.persist(user);
    }

    public boolean exist(String email) {
        return false;
    }

}
