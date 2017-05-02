package ar.edu.untref.gio.domain;

import ar.edu.untref.gio.domain.exception.InsufficientCurrenciesException;

import javax.persistence.*;

@Entity
@Table(name = "user_economy")
public class UserEconomy {

    @Id
    @Column(name = "id", length = 11, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id = 0;

    @Column
    private Double coins;

    public UserEconomy(Double coins) {
        this.coins = coins;
    }

    public Double getCoins() {
        return coins;
    }

    public Integer getId() {
        return id;
    }

    public void decrementCoins(Double amount) {
        if (amount > coins) {
            throw new InsufficientCurrenciesException();
        }
        coins -= amount;
    }
}