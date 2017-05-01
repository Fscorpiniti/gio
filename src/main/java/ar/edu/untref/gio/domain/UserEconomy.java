package ar.edu.untref.gio.domain;

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
}