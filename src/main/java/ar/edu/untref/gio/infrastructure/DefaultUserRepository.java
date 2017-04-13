package ar.edu.untref.gio.infrastructure;

import ar.edu.untref.gio.domain.User;
import ar.edu.untref.gio.domain.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.Optional;

@Repository("defaultUserRepository")
public class DefaultUserRepository extends GenericRepository<User> implements UserRepository {

    private static final String PARAM_EMAIL = "email";

    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public boolean exist(String email) {
        validateBlankEmail(email);
        Query query = buildQueryFindByEmail(email);
        return !query.getResultList().isEmpty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        validateBlankEmail(email);
        Query query = buildQueryFindByEmail(email);
        return query.getResultList().stream().findFirst();
    }

    private Query buildQueryFindByEmail(String email) {
        StringBuilder hql = new StringBuilder("from ")
                .append(getEntityClass().getName())
                .append(" this where this.email = :email");

        return this.getEntityManager()
                .createQuery(hql.toString())
                .setParameter(PARAM_EMAIL, email);
    }

    private void validateBlankEmail(String email) {
        if (StringUtils.isBlank(email)) {
            throw new IllegalArgumentException("Email is required");
        }
    }

}
