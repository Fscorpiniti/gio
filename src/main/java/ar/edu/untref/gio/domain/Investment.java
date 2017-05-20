package ar.edu.untref.gio.domain;

import com.google.gson.annotations.SerializedName;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.Date;
import java.util.Random;

public class Investment {

    private static final int MAX_PERCENTAGE = 100;
    private static final int ZERO = 0;

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

    public Investment() {}

    public Investment(Integer id, String text, Double interestLower, Double interestHigher, Double amount,
                      Boolean isPurchasable, String name) {
        this.id = id;
        this.text = text;
        this.interestLower = interestLower;
        this.interestHigher = interestHigher;
        this.amount = amount;
        this.isPurchasable = isPurchasable;
        this.name = name;
    }

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

    public Double calculateValueToBelieve(Date creation) {
        return getAmount() + calculateInterest(creation);
    }

    private Double calculateInterest(Date creation) {
        Date now = DateTime.now().toDate();
        int difference = Days.daysBetween(new LocalDate(creation),
                new LocalDate(now)).getDays();

        if (sameDate(difference)) {
            return new Random().doubles(ZERO, getInterestLower()).findFirst()
                    .getAsDouble() * getAmount() / MAX_PERCENTAGE;
        }

        return new Random().doubles(getInterestLower(), getInterestHigher())
                .findFirst().getAsDouble() * getAmount() * difference / MAX_PERCENTAGE;
    }

    private boolean sameDate(int difference) {
        return difference == 0;
    }
}
