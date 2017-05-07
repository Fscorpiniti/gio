package ar.edu.untref.gio.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import java.util.Date;

public class CreateTermDepositRequest {

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("rate")
    private Double rate;

    @JsonProperty("duration")
    private Integer duration;

    public CreateTermDepositRequest(){}

    public CreateTermDepositRequest(Double amount, Double rate, Integer duration) {
        this.amount = amount;
        this.rate = rate;
        this.duration = duration;
    }

    public Double getAmount() {
        return amount;
    }

    public Double getRate() {
        return rate;
    }

    public Integer getDuration() {
        return duration;
    }

    public Date getExpiration() {
        return DateTime.now().plusDays(getDuration()).toDate();
    }
}
