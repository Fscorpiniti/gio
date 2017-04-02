package ar.edu.untref.gio.security;

import ar.edu.untref.gio.domain.User;
import ar.edu.untref.gio.domain.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public class DefaultAuthenticationInteractor implements AuthenticationInteractor {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;

    public DefaultAuthenticationInteractor(AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public Optional<User> authenticate(String email, String password) {
        Authentication token = new UsernamePasswordAuthenticationToken(email, password);
        this.authenticationManager.authenticate(token);
        return userRepository.findByEmail(email);
    }

}
