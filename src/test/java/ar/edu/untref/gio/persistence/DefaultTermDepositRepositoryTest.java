package ar.edu.untref.gio.persistence;

import ar.edu.untref.gio.domain.TermDeposit;
import ar.edu.untref.gio.domain.TermDepositRepository;
import ar.edu.untref.gio.request.CreateTermDepositRequest;
import ar.edu.untref.gio.interactor.DefaultCreateTermDepositInteractor;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context-test.xml"})
public class DefaultTermDepositRepositoryTest {

    @Resource(name = "defaultTermDepositRepository")
    private TermDepositRepository termDepositRepository;

    private Integer ownerId;
    private List<TermDeposit> termDeposits;

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

    private void givenCreateTermDeposit() {
        Date validExpirationDate = new DateTime().plusDays(30).toDate();
        Double amount = new Double(100);
        Double rate = new Double(15);
        CreateTermDepositRequest createTermDepositRequest = new CreateTermDepositRequest(amount,
                rate, validExpirationDate);
        new DefaultCreateTermDepositInteractor(termDepositRepository).create(createTermDepositRequest, ownerId);
    }

    private void thenTermDepositsResultContainsElements() {
        Assert.assertEquals(1, termDeposits.size());
    }

    private void givenValidOwnerId() {
        ownerId = new Integer(1);
    }

    private void whenFindTermDepositsByOwnerId() {
        termDeposits = termDepositRepository.findByOwnerId(ownerId);
    }

    private void thenTermDepositsResultIsEmpty() {
        Assert.assertTrue(termDeposits.isEmpty());
    }

}
