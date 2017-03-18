package ar.edu.untref.gio.interactor;

import ar.edu.untref.gio.domain.TermDepositRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

public class FindTermDepositInteractorTest {

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void whenFindDepositsWithNullOwnerIdThenExceptionIsThrown() {
        Long ownerId = null;
        thrown.expect(NullPointerException.class);
        TermDepositRepository termDepositRepository = Mockito.mock(TermDepositRepository.class);
        new DefaultFindTermDepositInteractor(termDepositRepository).findByOwnerId(ownerId);
    }

}
