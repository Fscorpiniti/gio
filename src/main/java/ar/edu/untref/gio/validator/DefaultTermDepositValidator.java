package ar.edu.untref.gio.validator;

import com.google.common.base.Preconditions;
import org.joda.time.DateTime;

import java.util.Date;

public class DefaultTermDepositValidator implements TermDepositValidator {

    public static final String AMOUNT_IS_REQUIRED = "Amount is required";
    public static final String RATE_IS_REQUIRED = "Rate is required";
    public static final String EXPIRATION_IS_REQUIRED = "Expiration is required";
    public static final int MIN_VALID_DAYS = 29;
    public static final String CREATOR_ID_IS_REQUIRED = "Creator id is required";
    public static final String CREATOR_ID_IS_NOT_VALID = "creator id is not valid";
    public static final String EXPIRATION_IS_INVALID = "Expiration is invalid";

    @Override
    public void execute(Double amount, Double rate, Date expiration, Long creatorId) {
        Preconditions.checkNotNull(amount, AMOUNT_IS_REQUIRED);
        Preconditions.checkNotNull(rate, RATE_IS_REQUIRED);
        Preconditions.checkNotNull(expiration, EXPIRATION_IS_REQUIRED);
        Preconditions.checkNotNull(creatorId, CREATOR_ID_IS_REQUIRED);
        Preconditions.checkArgument(creatorId > 0l, CREATOR_ID_IS_NOT_VALID);

        Date minValidExpirationDate = new DateTime().plusDays(MIN_VALID_DAYS).toDate();
        Preconditions.checkArgument(expiration.after(minValidExpirationDate), EXPIRATION_IS_INVALID);
    }

}
