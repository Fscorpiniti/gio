package ar.edu.untref.gio.infrastructure;

import ar.edu.untref.gio.domain.TermDeposit;
import ar.edu.untref.gio.domain.TermDepositInformation;
import ar.edu.untref.gio.domain.TermDepositRepository;
import ar.edu.untref.gio.domain.request.CreateTermDepositRequest;
import ar.edu.untref.gio.domain.interactor.DefaultCreateTermDepositInteractor;
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
import java.util.Properties;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context-test.xml"})
public class DefaultTermDepositRepositoryTest {

    @Resource(name = "defaultTermDepositRepository")
    private TermDepositRepository termDepositRepository;

    @Resource(name = "props")
    private Properties properties;

    private Integer ownerId;
    private List<TermDeposit> termDeposits;
    private TermDepositInformation termDepositInformation;

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

    @Test
    public void whenFindTermDepositInformationForCreatedThenInformationIsReturned() {
        whenFindTermDepositInformation();

        thenTermDepositInformationIsNotNull();
    }

    @Test
    public void whenFindTermDepositInformationWithRatesThenRatesAreCorrect() {
        whenFindTermDepositInformation();

        thenRatesAreCorrect();
    }

    private void thenRatesAreCorrect() {
        Double monthlyRate = Double.valueOf(properties.getProperty("monthly.rate"));
        Double biMonthlyRate = Double.valueOf(properties.getProperty("biMonthly.rate"));
        Double quarterlyRate = Double.valueOf(properties.getProperty("quarterly.rate"));
        Double semiAnnualRate = Double.valueOf(properties.getProperty("semiAnnual.rate"));
        Double annualRate = Double.valueOf(properties.getProperty("annual.rate"));

        Assert.assertEquals(monthlyRate, termDepositInformation.getMonthlyRate());
        Assert.assertEquals(biMonthlyRate, termDepositInformation.getBiMonthlyRate());
        Assert.assertEquals(quarterlyRate, termDepositInformation.getQuarterlyRate());
        Assert.assertEquals(semiAnnualRate, termDepositInformation.getSemiAnnualRate());
        Assert.assertEquals(annualRate, termDepositInformation.getAnnualRate());
    }

    private void thenTermDepositInformationIsNotNull() {
        Assert.assertNotNull(termDepositInformation);
    }

    private void whenFindTermDepositInformation() {
        termDepositInformation = termDepositRepository.findTermDepositInformation();
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
        termDeposits = termDepositRepository.findActiveTermDepositsByOwnerId(ownerId);
    }

    private void thenTermDepositsResultIsEmpty() {
        Assert.assertTrue(termDeposits.isEmpty());
    }

}
