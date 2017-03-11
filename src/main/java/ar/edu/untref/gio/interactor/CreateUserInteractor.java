package ar.edu.untref.gio.interactor;

import ar.edu.untref.gio.domain.User;

public interface CreateUserInteractor {

    User create(String email, String password);

}