package ar.edu.untref.gio.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TermDepositInformationTest {

    private static final Double VALID_BI_MONTHLY_RATE = new Double(15);
    private static final Double VALID_QUARTERLY_RATE = new Double(20);
    private static final Double VALID_SEMIANNUAL_RATE = new Double(25);
    private static final Double VALID_ANNUAL_RATE = new Double(30);
    private static final Double VALID_MONTHLY_RATE = new Double(10);

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void whenCreateTermDepositInformationWithoutMonthlyRateThenExceptionIsThrown() {
        Double invalidMonthlyRate = null;
        thrown.expect(NullPointerException.class);
        new TermDepositInformation(invalidMonthlyRate, VALID_BI_MONTHLY_RATE, VALID_QUARTERLY_RATE, VALID_SEMIANNUAL_RATE, VALID_ANNUAL_RATE);
    }

    @Test
    public void whenCreateTermDepositInformationWithoutBiMonthlyRateThenExceptionIsThrown() {
        Double invalidBiMonthlyRate = null;
        thrown.expect(NullPointerException.class);
        new TermDepositInformation(VALID_MONTHLY_RATE, invalidBiMonthlyRate, VALID_QUARTERLY_RATE, VALID_SEMIANNUAL_RATE, VALID_ANNUAL_RATE);
    }

    @Test
    public void whenCreateTermDepositInformationWithoutQuarterlyRateThenExceptionIsThrown() {
        Double invalidQuarterlyRate = null;
        thrown.expect(NullPointerException.class);
        new TermDepositInformation(VALID_MONTHLY_RATE, VALID_BI_MONTHLY_RATE, invalidQuarterlyRate, VALID_SEMIANNUAL_RATE, VALID_ANNUAL_RATE);
    }

    @Test
    public void whenCreateTermDepositInformationWithoutSemiAnnualRateThenExceptionIsThrown() {
        Double invalidSemiAnnualRate = null;
        thrown.expect(NullPointerException.class);
        new TermDepositInformation(VALID_MONTHLY_RATE, VALID_BI_MONTHLY_RATE, VALID_QUARTERLY_RATE, invalidSemiAnnualRate, VALID_ANNUAL_RATE);
    }

    @Test
    public void whenCreateTermDepositInformationWithoutAnnualRateThenExceptionIsThrown() {
        Double invalidAnnualRate = null;
        thrown.expect(NullPointerException.class);
        new TermDepositInformation(VALID_MONTHLY_RATE, VALID_BI_MONTHLY_RATE, VALID_QUARTERLY_RATE, VALID_SEMIANNUAL_RATE, invalidAnnualRate);
    }

}
