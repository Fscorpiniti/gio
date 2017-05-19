package ar.edu.untref.gio.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InvestmentResponse {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("interest_higher")
    private Double interestHigher;

    @JsonProperty("interest_lower")
    private Double interestLower;

    @JsonProperty("purchasable")
    private Boolean purchasable;

    @JsonProperty("text")
    private String text;

    public InvestmentResponse(Integer id, Double amount, Double interestHigher, Double interestLower,
                              Boolean purchasable, String text) {
        this.id = id;
        this.amount = amount;
        this.interestHigher = interestHigher;
        this.interestLower = interestLower;
        this.purchasable = purchasable;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public Double getInterestHigher() {
        return interestHigher;
    }

    public Double getInterestLower() {
        return interestLower;
    }

    public Boolean getPurchasable() {
        return purchasable;
    }

    public String getText() {
        return text;
    }
}
