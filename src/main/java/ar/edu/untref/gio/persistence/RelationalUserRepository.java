package ar.edu.untref.gio.persistence;

import ar.edu.untref.gio.domain.User;
import ar.edu.untref.gio.domain.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

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
        StringBuilder hql = new StringBuilder("from ")
                .append(User.class.getName())
                .append(" this where this.email = :email");

        Query query = this.getEntityManager().createQuery(hql.toString());
        query.setParameter("email", email);

        return !query.getResultList().isEmpty();
    }

}
