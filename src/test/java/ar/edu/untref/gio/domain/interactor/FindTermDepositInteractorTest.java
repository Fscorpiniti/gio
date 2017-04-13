package ar.edu.untref.gio.domain.interactor;

import ar.edu.untref.gio.domain.TermDeposit;
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
