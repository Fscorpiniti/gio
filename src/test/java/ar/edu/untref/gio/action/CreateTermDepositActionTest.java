package ar.edu.untref.gio.action;

import ar.edu.untref.gio.domain.TermDeposit;
import ar.edu.untref.gio.domain.TermDepositRepository;
import ar.edu.untref.gio.dto.CreateTermDepositDTO;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;

public class CreateTermDepositActionTest {

    @Test
    public void whenCreateTermDepositThenTermDepositIsCreated() {
        Double amount = new Double(100);
        Double rate = new Double(15);
        int expirationIncrease = 1;
        Date expiration = new DateTime().plusDays(expirationIncrease).toDate();
        CreateTermDepositDTO createTermDepositDTO = new CreateTermDepositDTO(amount, rate, expiration);
        TermDepositRepository termDepositRepository = Mockito.mock(TermDepositRepository.class);

        TermDeposit termDeposit = new DefaultCreateTermDepositAction(termDepositRepository).create(createTermDepositDTO);

        Assert.assertNotNull(termDeposit);
    }

}
