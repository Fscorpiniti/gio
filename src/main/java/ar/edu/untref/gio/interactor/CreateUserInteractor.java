package ar.edu.untref.gio.interactor;

import ar.edu.untref.gio.domain.User;
import ar.edu.untref.gio.request.CreateUserRequest;

public interface CreateUserInteractor {

    User create(CreateUserRequest request);

}