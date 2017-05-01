package ar.edu.untref.gio.domain.interactor;

import ar.edu.untref.gio.domain.TermDeposit;
import ar.edu.untref.gio.domain.TermDepositInformation;
import ar.edu.untref.gio.domain.TermDepositRepository;
import ar.edu.untref.gio.domain.request.CreateTermDepositRequest;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FindTermDepositInteractorTest {

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    private Integer ownerId;
    private List<TermDeposit> termDeposits;
    private TermDepositRepository termDepositRepository = Mockito.mock(TermDepositRepository.class);
    private TermDepositInformation termDepositInformation;

    private static final Double VALID_BI_MONTHLY_RATE = new Double(15);
    private static final Double VALID_QUARTERLY_RATE = new Double(20);
    private static final Double VALID_SEMIANNUAL_RATE = new Double(25);
    private static final Double VALID_ANNUAL_RATE = new Double(30);
    private static final Double VALID_MONTHLY_RATE = new Double(10);

    @Test
    public void whenFindTermDepositsWithNullOwnerIdThenExceptionIsThrown() {
        givenNullOwnerId();

        thrown.expect(NullPointerException.class);
        whenFindTermDepositsByOwnerId();
    }

    @Test
    public void whenFindTermDepositsWithoutTermDepositsCreatedThenResultIsEmpty() {
        givenValidOwnerId();

        whenFindTermDepositsByOwnerId();

        thenTermDepositsResultIsEmpty();
    }

    @Test
    public void whenFindTermDepositsWithTermDepositsCreatedThenResultContainsElements() {
        givenValidOwnerId();
        givenCreateTermDeposit();

        whenFindTermDepositsByOwnerId();

        thenTermDepositsResultContainsElements();
    }

    @Test
    public void whenFindTermDepositInformationThenInformationIsReturned() {
        whenFindTermDepositInformation();

        thenTermDepositInformationIsNotNull();
    }

    @Test
    public void whenFindTermDepositInformationWithRatesThenRatesAreCorrect() {
        whenFindTermDepositInformation();

        thenRatesAreCorrect();
    }

    private void thenRatesAreCorrect() {
        Assert.assertEquals(VALID_MONTHLY_RATE, termDepositInformation.getMonthlyRate());
        Assert.assertEquals(VALID_BI_MONTHLY_RATE, termDepositInformation.getBiMonthlyRate());
        Assert.assertEquals(VALID_QUARTERLY_RATE, termDepositInformation.getQuarterlyRate());
        Assert.assertEquals(VALID_SEMIANNUAL_RATE, termDepositInformation.getSemiAnnualRate());
        Assert.assertEquals(VALID_ANNUAL_RATE, termDepositInformation.getAnnualRate());
    }

    private void thenTermDepositInformationIsNotNull() {
        Assert.assertNotNull(termDepositInformation);
    }

    private void whenFindTermDepositInformation() {
        Mockito.when(termDepositRepository.findTermDepositInformationForCreation()).thenReturn(new TermDepositInformation(VALID_MONTHLY_RATE,
                VALID_BI_MONTHLY_RATE, VALID_QUARTERLY_RATE, VALID_SEMIANNUAL_RATE, VALID_ANNUAL_RATE));
        termDepositInformation = new DefaultFindTermDepositInteractor(termDepositRepository).findTermDepositInformationForCreation();
    }

    private void thenTermDepositsResultContainsElements() {
        Assert.assertEquals(1, termDeposits.size());
    }

    private void givenCreateTermDeposit() {
        Date validExpirationDate = new DateTime().plusDays(30).toDate();
        Double amount = new Double(100);
        Double rate = new Double(15);
        CreateTermDepositRequest createTermDepositRequest = new CreateTermDepositRequest(amount,
                rate, validExpirationDate);
        TermDeposit termDeposit = new DefaultCreateTermDepositInteractor(termDepositRepository)
                .create(createTermDepositRequest, ownerId);
        Mockito.when(termDepositRepository.findActiveTermDepositsByOwnerId(ownerId)).thenReturn(Arrays.asList(termDeposit));
    }

    private void thenTermDepositsResultIsEmpty() {
        Assert.assertTrue(termDeposits.isEmpty());
    }

    private void givenValidOwnerId() {
        ownerId = new Integer(1);
    }

    private void givenNullOwnerId() {
        ownerId = null;
    }

    private void whenFindTermDepositsByOwnerId() {
        termDeposits = new DefaultFindTermDepositInteractor(termDepositRepository).findByOwnerId(ownerId);
    }

}
