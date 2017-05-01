package ar.edu.untref.gio.domain;

public class UserEconomyFactory {

    private Double initialCoins;

    public UserEconomyFactory(Double initialCoins) {
        this.initialCoins = initialCoins;
    }

    public UserEconomy buildInitialEconomy() {
        return new UserEconomy(initialCoins);
    }

}
