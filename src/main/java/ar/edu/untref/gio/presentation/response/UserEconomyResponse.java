package ar.edu.untref.gio.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserEconomyResponse {

    @JsonProperty("coins")
    private Double coins;

    public UserEconomyResponse(Double coins) {
        this.coins = coins;
    }

    public Double getCoins() {
        return coins;
    }

}
