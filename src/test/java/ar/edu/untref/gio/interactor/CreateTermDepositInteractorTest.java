package ar.edu.untref.gio.interactor;

import ar.edu.untref.gio.domain.TermDeposit;
import ar.edu.untref.gio.domain.TermDepositRepository;
import ar.edu.untref.gio.domain.TermDepositStatus;
import ar.edu.untref.gio.dto.CreateTermDepositRequest;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.util.Date;

public class CreateTermDepositInteractorTest {

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    private CreateTermDepositRequest createTermDepositRequest;
    private TermDeposit termDeposit;
    private Double validAmount = new Double(100);
    private Double validRate = new Double(15);
    private Date validExpirationDate = new DateTime().plusDays(30).toDate();
    private Long defaultCreatorId = new Long(1);

    @Test
    public void whenCreateTermDepositThenTermDepositIsCreated() {
        givenCreateTermDepositRequestWith(validAmount, validRate, validExpirationDate);

        whenCreateTermDeposit();

        thenTermDepositIsCreated();
    }

    @Test
    public void whenCreateTermDepositWithNullAmountThenExceptionIsThrown() {
        givenCreateTermDepositRequestWith(null, validRate, validExpirationDate);

        thrown.expect(NullPointerException.class);
        whenCreateTermDeposit();
    }

    @Test
    public void whenCreateTermDepositWithNullRateThenExceptionIsThrown() {
        givenCreateTermDepositRequestWith(validAmount, null, validExpirationDate);

        thrown.expect(NullPointerException.class);
        whenCreateTermDeposit();
    }

    @Test
    public void whenCreateTermDepositWithNullExpirationThenExceptionIsThrown() {
        givenCreateTermDepositRequestWith(validAmount, validRate, null);

        thrown.expect(NullPointerException.class);
        whenCreateTermDeposit();
    }

    @Test
    public void whenCreateTermDepositWithBeforeExpirationDateThenExceptionIsThrown() {
        int daysBeforeNow = 1;
        Date beforeNow = new DateTime().minusDays(daysBeforeNow).toDate();
        givenCreateTermDepositRequestWith(validAmount, validRate, beforeNow);

        thrown.expect(IllegalArgumentException.class);
        whenCreateTermDeposit();
    }

    @Test
    public void whenCreateTermDepositWithActualExpirationDateThenExceptionIsThrown() {
        Date now = new DateTime().toDate();
        givenCreateTermDepositRequestWith(validAmount, validRate, now);

        thrown.expect(IllegalArgumentException.class);
        whenCreateTermDeposit();
    }

    @Test
    public void whenCreateTermDepositWithLessThanThirtyExpirationDateThenExceptionIsThrown() {
        int daysAfterNow = 29;
        Date afterNow = new DateTime().minusDays(daysAfterNow).toDate();
        givenCreateTermDepositRequestWith(validAmount, validRate, afterNow);

        thrown.expect(IllegalArgumentException.class);
        whenCreateTermDeposit();
    }

    @Test
    public void whenCreateTermDepositThenTermDepositIsActive() {
        givenCreateTermDepositRequestWith(validAmount, validRate, validExpirationDate);

        whenCreateTermDeposit();

        thenTermDepositIsActive();
    }

    @Test
    public void whenCreateTermDepositThenTermDepositContainsCorrectAmount() {
        givenCreateTermDepositRequestWith(validAmount, validRate, validExpirationDate);

        whenCreateTermDeposit();

        thenTermDepositContainsCorrectAmount();
    }

    @Test
    public void whenCreateTermDepositThenTermDepositContainsCorrectRate() {
        givenCreateTermDepositRequestWith(validAmount, validRate, validExpirationDate);

        whenCreateTermDeposit();

        thenTermDepositContainsCorrectRate();
    }

    @Test
    public void whenCreateTermDepositThenTermDepositContainsCorrectExpirationDate() {
        givenCreateTermDepositRequestWith(validAmount, validRate, validExpirationDate);

        whenCreateTermDeposit();

        thenTermDepositContainsCorrectExpirationRate();
    }

    private void thenTermDepositContainsCorrectExpirationRate() {
        Assert.assertEquals(validExpirationDate, termDeposit.getExpiration());
    }

    private void thenTermDepositContainsCorrectRate() {
        Assert.assertEquals(validRate, termDeposit.getRate());
    }

    private void thenTermDepositContainsCorrectAmount() {
        Assert.assertEquals(validAmount, termDeposit.getAmount());
    }

    private void thenTermDepositIsActive() {
        Assert.assertEquals(TermDepositStatus.ACTIVE, termDeposit.getStatus());
    }

    private void givenCreateTermDepositRequestWith(Double amount, Double rate, Date expiration) {
        createTermDepositRequest = new CreateTermDepositRequest(amount, rate, expiration);
    }

    private void whenCreateTermDeposit() {
        TermDepositRepository termDepositRepository = Mockito.mock(TermDepositRepository.class);
        termDeposit = new DefaultCreateTermDepositInteractor(termDepositRepository).create(createTermDepositRequest, defaultCreatorId);
    }

    private void thenTermDepositIsCreated() {
        Assert.assertNotNull(termDeposit);
    }

}
