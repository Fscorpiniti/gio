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

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private UserEconomy userEconomy;

    protected User() {}

    public User(String email, String password, String name, UserValidator userValidator, UserEconomy userEconomy) {
        userValidator.execute(email, password, name, userEconomy);
        this.email = email;
        this.password = password;
        this.name = name;
        this.userEconomy = userEconomy;
    }

    public User(String email, String password, String name, UserValidator userValidator, UserEconomy userEconomy,
                Integer id) {
        userValidator.execute(email, password, name, userEconomy);
        this.email = email;
        this.password = password;
        this.name = name;
        this.userEconomy = userEconomy;
        this.id = id;
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

    public UserEconomy getUserEconomy() {
        return userEconomy;
    }

    public void decrementCoins(Double amount) {
        this.userEconomy.decrementCoins(amount);
    }

    public void incrementCoins(Double amount) {
        this.userEconomy.incrementCoins(amount);
    }
}