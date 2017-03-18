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

    @Test
    public void whenFindTermDepositsWithNullOwnerIdThenExceptionIsThrown() {
        Long ownerId = null;
        thrown.expect(NullPointerException.class);
        TermDepositRepository termDepositRepository = Mockito.mock(TermDepositRepository.class);
        new DefaultFindTermDepositInteractor(termDepositRepository).findByOwnerId(ownerId);
    }

    @Test
    public void whenFindTermDepositsWithoutTermDepositsCreatedThenResultIsEmpty() {
        Long ownerId = 1l;
        TermDepositRepository termDepositRepository = Mockito.mock(TermDepositRepository.class);
        List<TermDeposit> termDeposits = new DefaultFindTermDepositInteractor(termDepositRepository).findByOwnerId(ownerId);

        Assert.assertTrue(termDeposits.isEmpty());
    }

}
