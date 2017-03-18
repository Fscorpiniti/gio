package ar.edu.untref.gio.interactor;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FindTermDepositInteractorTest {

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void whenFindDepositsWithNullOwnerIdThenExceptionIsThrown() {
        Long ownerId = null;
        thrown.expect(NullPointerException.class);
        new DefaultFindTermDepositInteractor().findByOwnerId(ownerId);
    }

}
