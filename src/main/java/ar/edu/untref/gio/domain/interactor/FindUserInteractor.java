package ar.edu.untref.gio.domain.interactor;

import ar.edu.untref.gio.domain.User;

import java.util.Optional;

public interface FindUserInteractor {

    Optional<User> findById(Integer id);

}
