package ar.edu.untref.gio.domain;

import ar.edu.untref.gio.domain.exception.InsufficientCurrenciesException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "user_economy")
public class UserEconomy {

    private static final int SCALE = 2;

    @Id
    @Column(name = "id", length = 11, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id = 0;

    @Column
    private Double coins;

    public UserEconomy() {}

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
        coins -= new BigDecimal(amount).setScale(SCALE, RoundingMode.HALF_UP).doubleValue();
    }

    public void incrementCoins(Double amount) {
        coins += new BigDecimal(amount).setScale(SCALE, RoundingMode.HALF_UP).doubleValue();
    }
}