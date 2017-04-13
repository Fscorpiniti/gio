package ar.edu.untref.gio.domain.interactor;

import ar.edu.untref.gio.domain.User;
import ar.edu.untref.gio.domain.request.CreateUserRequest;

public interface CreateUserInteractor {

    User create(CreateUserRequest request);

}