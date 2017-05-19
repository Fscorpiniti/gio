package ar.edu.untref.gio.domain;

import com.google.gson.annotations.SerializedName;

public class Investment {

    @SerializedName("id")
    private Integer id;

    @SerializedName("text")
    private String text;

    @SerializedName("interest_lower")
    private Double interestLower;

    @SerializedName("interest_higher")
    private Double interestHigher;

    @SerializedName("amount")
    private Double amount;

    @SerializedName("is_purchasable")
    private Boolean isPurchasable;

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Double getInterestLower() {
        return interestLower;
    }

    public Double getInterestHigher() {
        return interestHigher;
    }

    public Double getAmount() {
        return amount;
    }

    public Boolean getPurchasable() {
        return isPurchasable;
    }
}
