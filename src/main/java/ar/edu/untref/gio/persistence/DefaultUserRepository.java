package ar.edu.untref.gio.persistence;

import ar.edu.untref.gio.domain.User;
import ar.edu.untref.gio.domain.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository("defaultUserRepository")
public class DefaultUserRepository extends GenericRepository<User> implements UserRepository {

    private static final String PARAM_EMAIL = "email";

    protected Class<User> getEntityClass() {
        return User.class;
    }

    public boolean exist(String email) {
        StringBuilder hql = new StringBuilder("from ")
                .append(getEntityClass().getName())
                .append(" this where this.email = :email");

        Query query = this.getEntityManager().createQuery(hql.toString());
        query.setParameter(PARAM_EMAIL, email);

        return !query.getResultList().isEmpty();
    }

}
