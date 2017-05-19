package ar.edu.untref.gio.domain;

import ar.edu.untref.gio.domain.validator.TermDepositValidator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "term_deposit")
public class TermDeposit {

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

    protected TermDeposit(){}

    public TermDeposit(Double amount, Double rate, Date expiration, TermDepositValidator validator, Integer ownerId) {
        validator.execute(amount, rate, expiration, ownerId);
        this.ownerId = ownerId;
        this.amount = amount;
        this.rate = rate;
        this.expiration = expiration;
        this.status = TermDepositStatus.ACTIVE;
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
        return getAmount() + calculateInterest();
    }

    @Transient
    private double calculateInterest() {
        return getAmount() * getRate() / 100;
    }
}
