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

    @Test
    public void whenCreateTermDepositThenTermDepositIsCreated() {
        givenCreateTermDepositDTOWith(new Double(100), new Double(15), new DateTime().plusDays(30).toDate());

        whenCreateTermDeposit();

        thenTermDepositIsCreated();
    }

    @Test
    public void whenCreateTermDepositWithNullAmountThenExceptionIsThrown() {
        givenCreateTermDepositDTOWith(null, new Double(15), new Date());

        thrown.expect(NullPointerException.class);
        whenCreateTermDeposit();
    }

    @Test
    public void whenCreateTermDepositWithNullRateThenExceptionIsThrown() {
        givenCreateTermDepositDTOWith(new Double(100), null, new Date());

        thrown.expect(NullPointerException.class);
        whenCreateTermDeposit();
    }

    @Test
    public void whenCreateTermDepositWithNullExpirationThenExceptionIsThrown() {
        givenCreateTermDepositDTOWith(new Double(100), new Double(15), null);

        thrown.expect(NullPointerException.class);
        whenCreateTermDeposit();
    }

    @Test
    public void whenCreateTermDepositWithBeforeExpirationDateThenExceptionIsThrown() {
        givenCreateTermDepositDTOWith(new Double(100), new Double(15), new DateTime().minusDays(1).toDate());

        thrown.expect(IllegalArgumentException.class);
        whenCreateTermDeposit();
    }

    @Test
    public void whenCreateTermDepositWithActualExpirationDateThenExceptionIsThrown() {
        givenCreateTermDepositDTOWith(new Double(100), new Double(15), new DateTime().toDate());

        thrown.expect(IllegalArgumentException.class);
        whenCreateTermDeposit();
    }

    @Test
    public void whenCreateTermDepositWithLessThanThirtyExpirationDateThenExceptionIsThrown() {
        givenCreateTermDepositDTOWith(new Double(100), new Double(15), new DateTime().plusDays(29).toDate());

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
