package ar.edu.untref.gio.security;

import ar.edu.untref.gio.domain.User;

import java.util.Optional;

public interface AuthenticationInteractor {

    Optional<User> authenticate(String email, String password);

}
