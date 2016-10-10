package ar.edu.untref.gio.persistence;

import ar.edu.untref.gio.domain.User;
import ar.edu.untref.gio.domain.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository("relationalUserRepository")
public class RelationalUserRepository extends GenericRepository<User> implements UserRepository {

    private static final String PARAM_EMAIL = "email";

    public boolean exist(String email) {
        StringBuilder hql = new StringBuilder("from ")
                .append(User.class.getName())
                .append(" this where this.email = :email");

        Query query = this.getEntityManager().createQuery(hql.toString());
        query.setParameter(PARAM_EMAIL, email);

        return !query.getResultList().isEmpty();
    }

}
