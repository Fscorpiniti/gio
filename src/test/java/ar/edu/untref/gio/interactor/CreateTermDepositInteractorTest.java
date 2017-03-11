package ar.edu.untref.gio.interactor;

import ar.edu.untref.gio.domain.TermDeposit;
import ar.edu.untref.gio.domain.TermDepositRepository;
import ar.edu.untref.gio.dto.CreateTermDepositDTO;
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

    private CreateTermDepositDTO createTermDepositDTO;
    private TermDeposit termDeposit;
    private Double validAmount = new Double(100);
    private Double validRate = new Double(15);
    private Date validExpirationDate = new DateTime().plusDays(30).toDate();

    @Test
    public void whenCreateTermDepositThenTermDepositIsCreated() {
        givenCreateTermDepositDTOWith(validAmount, validRate, validExpirationDate);

        whenCreateTermDeposit();

        thenTermDepositIsCreated();
    }

    @Test
    public void whenCreateTermDepositWithNullAmountThenExceptionIsThrown() {
        givenCreateTermDepositDTOWith(null, validRate, validExpirationDate);

        thrown.expect(NullPointerException.class);
        whenCreateTermDeposit();
    }

    @Test
    public void whenCreateTermDepositWithNullRateThenExceptionIsThrown() {
        givenCreateTermDepositDTOWith(validAmount, null, validExpirationDate);

        thrown.expect(NullPointerException.class);
        whenCreateTermDeposit();
    }

    @Test
    public void whenCreateTermDepositWithNullExpirationThenExceptionIsThrown() {
        givenCreateTermDepositDTOWith(validAmount, validRate, null);

        thrown.expect(NullPointerException.class);
        whenCreateTermDeposit();
    }

    @Test
    public void whenCreateTermDepositWithBeforeExpirationDateThenExceptionIsThrown() {
        int daysBeforeNow = 1;
        Date beforeNow = new DateTime().minusDays(daysBeforeNow).toDate();
        givenCreateTermDepositDTOWith(validAmount, validRate, beforeNow);

        thrown.expect(IllegalArgumentException.class);
        whenCreateTermDeposit();
    }

    @Test
    public void whenCreateTermDepositWithActualExpirationDateThenExceptionIsThrown() {
        Date now = new DateTime().toDate();
        givenCreateTermDepositDTOWith(validAmount, validRate, now);

        thrown.expect(IllegalArgumentException.class);
        whenCreateTermDeposit();
    }

    @Test
    public void whenCreateTermDepositWithLessThanThirtyExpirationDateThenExceptionIsThrown() {
        int daysAfterNow = 29;
        Date afterNow = new DateTime().minusDays(daysAfterNow).toDate();
        givenCreateTermDepositDTOWith(validAmount, validRate, afterNow);

        thrown.expect(IllegalArgumentException.class);
        whenCreateTermDeposit();
    }

    private void givenCreateTermDepositDTOWith(Double amount, Double rate, Date expiration) {
        createTermDepositDTO = new CreateTermDepositDTO(amount, rate, expiration);
    }

    private void whenCreateTermDeposit() {
        TermDepositRepository termDepositRepository = Mockito.mock(TermDepositRepository.class);
        termDeposit = new DefaultCreateTermDepositInteractor(termDepositRepository).create(createTermDepositDTO);
    }

    private void thenTermDepositIsCreated() {
        Assert.assertNotNull(termDeposit);
    }

}
