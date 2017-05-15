package ar.edu.untref.gio.domain.interactor;

import ar.edu.untref.gio.domain.User;
import ar.edu.untref.gio.domain.UserRepository;
import ar.edu.untref.gio.domain.service.RemoveTokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public class DefaultAuthenticationInteractor implements AuthenticationInteractor {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RemoveTokenService removeTokenService;

    public DefaultAuthenticationInteractor(AuthenticationManager authenticationManager, UserRepository userRepository,
                                           RemoveTokenService removeTokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.removeTokenService = removeTokenService;
    }

    public Optional<User> authenticate(String email, String password) {
        Authentication token = new UsernamePasswordAuthenticationToken(email, password);
        this.authenticationManager.authenticate(token);
        Optional<User> userOptional = userRepository.findByEmail(email);
        this.removeTokenService.remove(userOptional.get().getId());
        return userOptional;
    }

}
