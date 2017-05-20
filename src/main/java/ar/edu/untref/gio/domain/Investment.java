package ar.edu.untref.gio.domain;

import com.google.gson.annotations.SerializedName;

import java.util.Random;

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

    @SerializedName("name")
    private String name;

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

    public String getName() {
        return name;
    }

    public Double calculateValueToBelieve() {
        return getAmount() + calculateInterest();
    }

    private Double calculateInterest() {
        return new Random().doubles(getInterestLower(), getInterestHigher())
                .findFirst().getAsDouble() * getAmount() / 100;
    }
}
