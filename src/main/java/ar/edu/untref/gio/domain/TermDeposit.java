package ar.edu.untref.gio.domain;

import ar.edu.untref.gio.domain.validator.DefaultTermDepositValidator;
import ar.edu.untref.gio.domain.validator.TermDepositValidator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Entity
@Table(name = "term_deposit")
public class TermDeposit {

    private static final int AMOUNT_DAYS_YEAR = 365;
    private static final int MAX_PERCENTAGE = 100;
    private static final int SCALE = 2;

    @Id
    @Column(name = "id", length = 11, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id = 0;

    @Column
    private Double amount;

    @Column
    private Double rate;

    @Column(name = "expiration_date")
    private Date expiration;

    @Column(name = "owner_id")
    private Integer ownerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TermDepositStatus status;

    @Column(name = "duration")
    private Integer duration;

    public TermDeposit() {}

    public TermDeposit(Double amount, Double rate, Date expiration, TermDepositValidator validator, Integer ownerId,
                       Integer duration) {
        validator.execute(amount, rate, expiration, ownerId, duration);
        this.ownerId = ownerId;
        this.amount = amount;
        this.rate = rate;
        this.expiration = expiration;
        this.status = TermDepositStatus.ACTIVE;
        this.duration = duration;
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

    public Integer getId() {
        return id;
    }

    public TermDepositStatus getStatus() {
        return status;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    @Transient
    public void finalize() {
        this.status = TermDepositStatus.FINALIZED;
    }

    @Transient
    public Double calculateValueToBelieve() {
        Double finalAmount = getAmount() + calculateInterest();
        return new BigDecimal(finalAmount).setScale(SCALE, RoundingMode.HALF_UP).doubleValue();
    }

    @Transient
    private double calculateInterest() {
        return (getAmount() * getDuration() * getRate()) / (MAX_PERCENTAGE * AMOUNT_DAYS_YEAR);
    }

    public Integer getDuration() {
        return duration;
    }
}
