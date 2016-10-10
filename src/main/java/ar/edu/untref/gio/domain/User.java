package ar.edu.untref.gio.domain;

import ar.edu.untref.gio.validator.UserValidator;

public class User {

    private String email;
    private String password;

    public User(String email, String password, UserValidator userValidator) {
        userValidator.validate(email, password);
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

}