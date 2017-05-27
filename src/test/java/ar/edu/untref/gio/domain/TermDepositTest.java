package ar.edu.untref.gio.domain;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class TermDepositTest {

    public static final int THIRTY_DAYS = 30;

    @Test
    public void whenCalculateValueToBelieveThenResultIsValid() {
        Double amount = new Double(1000);
        Double rate = new Double(15);
        Integer ownerId = new Integer(1);
        Date expiration = new DateTime().plusDays(THIRTY_DAYS).toDate();

        TermDeposit termDeposit = new TermDepositBuilder().withRate(rate).withAmount(amount)
                .withExpiration(expiration).withOwnerId(ownerId).withDuration(THIRTY_DAYS).build();

        Double expectedValue = new Double(1012.33);
        Assert.assertEquals(expectedValue, termDeposit.calculateValueToBelieve());
    }

}
