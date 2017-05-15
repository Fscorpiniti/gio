package ar.edu.untref.gio.domain;

import java.util.List;

public interface TokenRepository extends Repository<Token> {

    List<Token> findBy(Integer userId, String token);
}
