package ar.edu.untref.gio.configuration;

import ar.edu.untref.gio.controller.LoginController;
import ar.edu.untref.gio.domain.UserRepository;
import ar.edu.untref.gio.security.AuthenticationInteractor;
import ar.edu.untref.gio.security.DefaultAuthenticationInteractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class AuthenticationConfiguration {

    @Bean
    @Scope("prototype")
    public LoginController loginController(AuthenticationInteractor authenticationInteractor) {
        return new LoginController(authenticationInteractor);
    }

    @Bean
    public AuthenticationInteractor authenticationInteractor(AuthenticationManager authenticationManager, UserRepository userRepository) {
        return new DefaultAuthenticationInteractor(authenticationManager, userRepository);
    }

}
