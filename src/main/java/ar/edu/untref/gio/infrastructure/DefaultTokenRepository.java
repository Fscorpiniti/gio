package ar.edu.untref.gio.infrastructure;

import ar.edu.untref.gio.domain.Token;
import ar.edu.untref.gio.domain.TokenRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository("defaultTokenRepository")
public class DefaultTokenRepository extends GenericRepository<Token> implements TokenRepository {

    @Override
    protected Class<Token> getEntityClass() {
        return Token.class;
    }

    @Override
    public List<Token> findBy(Integer userId, String token) {
        StringBuilder hql = new StringBuilder("from ");
        hql.append(getEntityClass().getName());
        hql.append(" this where this.value = :token AND this.userId = :userId");

        Query query = this.getEntityManager().createQuery(hql.toString());
        query.setParameter("token", token);
        query.setParameter("userId", userId);

        return query.getResultList();
    }
}
