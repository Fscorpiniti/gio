package ar.edu.untref.gio.domain;

import ar.edu.untref.gio.validator.UserValidator;

import javax.persistence.*;

@Entity
public class User {

    @Column
    private String email;

    @Column
    private String password;

    @Id
    @Column(name = "id", length = 11, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id = 0;

    public User(String email, String password, UserValidator userValidator) {
        userValidator.validate(email, password);
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

}