package ar.edu.untref.gio.validator;

import com.google.common.base.Preconditions;

import java.util.Date;

public class DefaultTermDepositValidator implements TermDepositValidator {

    public static final String AMOUNT_IS_REQUIRED = "Amount is required";
    public static final String RATE_IS_REQUIRED = "Rate is required";
    public static final String EXPIRATION_IS_REQUIRED = "Expiration is required";

    @Override
    public void execute(Double amount, Double rate, Date expiration) {
        Preconditions.checkNotNull(amount, AMOUNT_IS_REQUIRED);
        Preconditions.checkNotNull(rate, RATE_IS_REQUIRED);
        Preconditions.checkNotNull(expiration, EXPIRATION_IS_REQUIRED);
    }

}
