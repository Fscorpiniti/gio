package ar.edu.untref.gio.interactor;

import ar.edu.untref.gio.domain.TermDeposit;
import ar.edu.untref.gio.domain.TermDepositRepository;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.util.List;

public class FindTermDepositInteractorTest {

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    private Long ownerId;
    private List<TermDeposit> termDeposits;

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

    private void thenTermDepositsResultIsEmpty() {
        Assert.assertTrue(termDeposits.isEmpty());
    }

    private void givenValidOwnerId() {
        ownerId = new Long(1);
    }

    private void givenNullOwnerId() {
        ownerId = null;
    }

    private void whenFindTermDepositsByOwnerId() {
        TermDepositRepository termDepositRepository = Mockito.mock(TermDepositRepository.class);
        termDeposits = new DefaultFindTermDepositInteractor(termDepositRepository).findByOwnerId(ownerId);
    }

}
