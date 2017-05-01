package ar.edu.untref.gio.domain.validator;

import ar.edu.untref.gio.domain.UserEconomy;

public interface UserValidator {

    void execute(String email, String password, String name, UserEconomy userEconomy);

}
