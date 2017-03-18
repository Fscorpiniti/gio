package ar.edu.untref.gio.request;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

public class CreateTermDepositRequest {

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("rate")
    private Double rate;

    @JsonProperty("expiration_date")
    private Date expiration;

    public CreateTermDepositRequest(){}

    public CreateTermDepositRequest(Double amount, Double rate, Date expiration) {
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
