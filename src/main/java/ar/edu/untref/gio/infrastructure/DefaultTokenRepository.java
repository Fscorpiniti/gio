package ar.edu.untref.gio.infrastructure;

import ar.edu.untref.gio.domain.Token;
import ar.edu.untref.gio.domain.TokenRepository;
import org.springframework.stereotype.Repository;

@Repository("defaultTokenRepository")
public class DefaultTokenRepository extends GenericRepository<Token> implements TokenRepository {

    @Override
    protected Class<Token> getEntityClass() {
        return Token.class;
    }
}
