package ar.edu.untref.gio.domain;

import ar.edu.untref.gio.domain.validator.DefaultTermDepositValidator;

import java.util.Date;

public class TermDepositBuilder {

    private Integer ownerId;
    private Double rate;
    private Double amount;
    private Date expiration;
    private TermDepositStatus status;

    public TermDepositBuilder withOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public TermDepositBuilder withRate(Double rate) {
        this.rate = rate;
        return this;
    }

    public TermDepositBuilder withAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public TermDepositBuilder withExpiration(Date expiration) {
        this.expiration = expiration;
        return this;
    }

    public TermDeposit build() {
        return new TermDeposit(this.amount, this.rate, this.expiration,
                new DefaultTermDepositValidator(), this.ownerId);
    }

}
