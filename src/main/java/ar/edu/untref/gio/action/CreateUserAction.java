package ar.edu.untref.gio.action;

import ar.edu.untref.gio.domain.User;

public interface CreateUserAction {

    User create(String email, String password);

}