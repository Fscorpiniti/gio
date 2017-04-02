package ar.edu.untref.gio.configuration;

import ar.edu.untref.gio.domain.UserRepository;
import ar.edu.untref.gio.security.AuthenticationInteractor;
import ar.edu.untref.gio.security.DefaultAuthenticationInteractor;
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
