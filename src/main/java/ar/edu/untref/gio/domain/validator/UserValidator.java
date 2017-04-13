package ar.edu.untref.gio.domain.validator;

public interface UserValidator {

    void execute(String email, String password, String name);

}
