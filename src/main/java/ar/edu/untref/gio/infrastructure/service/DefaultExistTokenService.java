package ar.edu.untref.gio.infrastructure.service;

import ar.edu.untref.gio.domain.Token;
import ar.edu.untref.gio.domain.TokenRepository;
import ar.edu.untref.gio.domain.service.ExistTokenService;
import ar.edu.untref.gio.infrastructure.exception.UserIllegalAccessException;

import java.util.List;

public class DefaultExistTokenService implements ExistTokenService {

    private TokenRepository tokenRepository;

    public DefaultExistTokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void exist(Integer userId, String token) {
        List<Token> tokens = tokenRepository.findBy(userId, token);
        if (tokens.isEmpty()) {
            throw new UserIllegalAccessException();
        }
    }
}
