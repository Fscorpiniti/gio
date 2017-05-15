package ar.edu.untref.gio.domain.configuration;

import ar.edu.untref.gio.domain.TokenRandomAlphanumericGenerator;
import ar.edu.untref.gio.domain.TokenRepository;
import ar.edu.untref.gio.domain.service.CreateTokenService;
import ar.edu.untref.gio.domain.service.DefaultCreateTokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenConfiguration {

    private static final int TOKEN_LENGTH = 16;

    @Bean
    public CreateTokenService createTokenService(TokenRepository tokenRepository) {
        return new DefaultCreateTokenService(new TokenRandomAlphanumericGenerator(TOKEN_LENGTH), tokenRepository);
    }

}
