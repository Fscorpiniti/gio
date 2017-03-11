package ar.edu.untref.gio.validator;

import com.google.common.base.Preconditions;

import java.util.Date;

public class DefaultTermDepositValidator implements TermDepositValidator {

    @Override
    public void execute(Double amount, Double rate, Date expiration) {
        Preconditions.checkNotNull(amount, "Amount is required");
        Preconditions.checkNotNull(rate, "Rate is required");
        Preconditions.checkNotNull(expiration, "Expiration is required");
    }

}
