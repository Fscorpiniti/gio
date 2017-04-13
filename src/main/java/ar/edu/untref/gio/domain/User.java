package ar.edu.untref.gio.domain;

import ar.edu.untref.gio.domain.validator.UserValidator;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id", length = 11, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id = 0;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String name;

    protected User() {}

    public User(String email, String password, String name, UserValidator userValidator) {
        userValidator.execute(email, password, name);
        this.email = email;
        this.password = password;
        this.name = name;
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

    public String getName() {
        return name;
    }
}