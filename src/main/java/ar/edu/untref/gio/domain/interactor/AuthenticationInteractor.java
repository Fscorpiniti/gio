package ar.edu.untref.gio.domain.interactor;

import ar.edu.untref.gio.domain.User;

import java.util.Optional;

public interface AuthenticationInteractor {

    Optional<User> authenticate(String email, String password);

}
