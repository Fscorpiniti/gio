package ar.edu.untref.gio.presentation.response;

import ar.edu.untref.gio.domain.TermDepositStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class TermDepositResponse {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("rate")
    private Double rate;

    @JsonProperty("expiration_date")
    private Date expiration;

    @JsonProperty("status")
    private TermDepositStatus status;

    @JsonProperty("owner_id")
    private Integer ownerId;

    public TermDepositResponse(Integer id, Double amount, Double rate, Date expiration,
                               TermDepositStatus status, Integer ownerId) {
        this.id = id;
        this.amount = amount;
        this.rate = rate;
        this.expiration = expiration;
        this.status = status;
        this.ownerId = ownerId;
    }

    public Integer getId() {
        return id;
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

    public TermDepositStatus getStatus() {
        return status;
    }

    public Integer getOwnerId() {
        return ownerId;
    }
}
