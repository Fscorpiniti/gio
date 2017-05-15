package ar.edu.untref.gio.infrastructure.service;

import ar.edu.untref.gio.domain.TokenRepository;
import ar.edu.untref.gio.domain.service.RemoveTokenService;

public class DefaultRemoveTokenService implements RemoveTokenService {

    private TokenRepository tokenRepository;

    public DefaultRemoveTokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void remove(Integer userId) {
        tokenRepository.remove(userId);
    }
}
