package ar.edu.untref.gio.infrastructure.service;

import ar.edu.untref.gio.domain.Token;
import ar.edu.untref.gio.domain.TokenGenerator;
import ar.edu.untref.gio.domain.TokenRepository;
import ar.edu.untref.gio.domain.service.CreateTokenService;

public class DefaultCreateTokenService implements CreateTokenService {

    private TokenGenerator tokenGenerator;
    private TokenRepository tokenRepository;

    public DefaultCreateTokenService(TokenGenerator tokenGenerator, TokenRepository tokenRepository) {
        this.tokenGenerator = tokenGenerator;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public String create(Integer userId) {
        Token token = new Token(userId, tokenGenerator.generate());
        tokenRepository.add(token);
        return token.getValue();
    }
}
