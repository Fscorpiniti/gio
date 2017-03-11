package ar.edu.untref.gio.dto;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

public class CreateTermDepositDTO {

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("rate")
    private Double rate;

    @JsonProperty("expiration_date")
    private Date expiration;

    public CreateTermDepositDTO(){}

    public CreateTermDepositDTO(Double amount, Double rate, Date expiration) {
        this.amount = amount;
        this.rate = rate;
        this.expiration = expiration;
    }

    public Double getAmount() {
        return amount;
    }

    public Double getRate() {
        return rate;
    }

    public Date getExpiration() {
        return expiration;
    }

}
