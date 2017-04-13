package ar.edu.untref.gio.domain.configuration;

import ar.edu.untref.gio.domain.UserRepository;
import ar.edu.untref.gio.domain.interactor.AuthenticationInteractor;
import ar.edu.untref.gio.domain.interactor.DefaultAuthenticationInteractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class AuthenticationConfiguration {

    @Bean
    public AuthenticationInteractor authenticationInteractor(AuthenticationManager authenticationManager, UserRepository userRepository) {
        return new DefaultAuthenticationInteractor(authenticationManager, userRepository);
    }

}
