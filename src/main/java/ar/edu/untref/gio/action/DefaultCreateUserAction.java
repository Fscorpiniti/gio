package ar.edu.untref.gio.action;

import ar.edu.untref.gio.domain.User;
import ar.edu.untref.gio.validator.DefaultUserValidator;

public class DefaultCreateUserAction implements CreateUserAction {

    public User create(String email, String password) {
        User user = new User(email, password, new DefaultUserValidator());
        return user;
    }

}