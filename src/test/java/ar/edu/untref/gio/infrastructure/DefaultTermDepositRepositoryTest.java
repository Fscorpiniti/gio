package ar.edu.untref.gio.infrastructure;

import ar.edu.untref.gio.domain.*;
import ar.edu.untref.gio.domain.interactor.FindUserInteractor;
import ar.edu.untref.gio.domain.request.CreateTermDepositRequest;
import ar.edu.untref.gio.domain.interactor.DefaultCreateTermDepositInteractor;
import ar.edu.untref.gio.domain.service.DefaultUserCurrencyDomainService;
import ar.edu.untref.gio.domain.service.UserCurrencyDomainService;
import ar.edu.untref.gio.domain.validator.DefaultUserValidator;
import ar.edu.untref.gio.infrastructure.exception.ObjectNotFoundException;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context-test.xml"})
@WebAppConfiguration
public class DefaultTermDepositRepositoryTest {

    @Resource(name = "defaultTermDepositRepository")
    private TermDepositRepository termDepositRepository;

    @Resource(name = "props")
    private Properties properties;

    @Resource(name = "defaultUserRepository")
    private UserRepository userRepository;

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    private User owner;
    private List<TermDeposit> termDeposits;
    private TermDepositInformation termDepositInformation;

    private static final String VALID_EMAIL = "test@gio.com";
    private static final String VALID_PASSWORD = "auth";
    private static final String VALID_NAME = "test";
    private static final Double INITIAL_COINS = new Double(1000);
    private static final int DEFAULT_DURATION = 30;
    private static final int DEFAULT_AMOUNT = 100;
    private static final int DEFAULT_RATE = 15;
    private TermDeposit termDeposit;

    @Test
    public void whenFindTermDepositsWithoutTermDepositsCreatedThenResultIsEmpty() {
        givenDefaultUser();

        whenFindTermDepositsByOwnerId();

        thenTermDepositsResultIsEmpty();
    }

    @Test
    public void whenFindTermDepositsWithTermDepositsCreatedThenResultContainsElements() {
        givenDefaultUser();
        givenDefaultTermDeposit();

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

    @Test
    public void whenFindTermDepositForExpiredWithoutTermDepositsThenResultIsEmpty() {
        Date expiration = new DateTime().plusDays(DEFAULT_DURATION).toDate();
        List<TermDeposit> termDeposits = termDepositRepository.findTermDepositToExpire(expiration);
        Assert.assertTrue(termDeposits.isEmpty());
    }

    @Test
    public void whenFindTermDepositForExpiredWithTermDepositsThenResultIsCorrect() {
        givenDefaultUser();
        givenDefaultTermDeposit();

        Date expiration = new DateTime().plusDays(DEFAULT_DURATION).toDate();
        termDeposits = termDepositRepository.findTermDepositToExpire(expiration);

        thenTermDepositsResultContainsElements();
    }

    @Test
    public void whenFindTermDepositWithInexistentTermDepositIdThenExceptionIsThrown() {
        givenDefaultUser();
        Integer inexistentTermDepositId = 1;

        thrown.expect(ObjectNotFoundException.class);
        termDepositRepository.findBy(owner.getId(), inexistentTermDepositId);
    }

    @Test
    public void whenFindTermDepositWithValidTermDepositIdThenResultIsCorrect() {
        givenDefaultUser();
        givenDefaultTermDeposit();

        TermDeposit termDepositById = termDepositRepository.findBy(owner.getId(), termDeposit.getId());

        Assert.assertNotNull(termDepositById);
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
        termDepositInformation = termDepositRepository.findTermDepositInformationForCreation();
    }

    private void givenDefaultTermDeposit() {
        Integer duration = new Integer(DEFAULT_DURATION);
        Double amount = new Double(DEFAULT_AMOUNT);
        Double rate = new Double(DEFAULT_RATE);
        CreateTermDepositRequest createTermDepositRequest = new CreateTermDepositRequest(amount,
                rate, duration);
        FindUserInteractor findUserInteractor = Mockito.mock(FindUserInteractor.class);
        Mockito.when(findUserInteractor.findById(owner.getId())).thenReturn(Optional.of(owner));
        UserCurrencyDomainService userCurrencyDomainService = new DefaultUserCurrencyDomainService();
        UserRepository userRepository = Mockito.mock(UserRepository.class);

        termDeposit = new DefaultCreateTermDepositInteractor(termDepositRepository, findUserInteractor,
                userCurrencyDomainService, userRepository).create(createTermDepositRequest, owner.getId());
    }

    private void thenTermDepositsResultContainsElements() {
        Assert.assertEquals(1, termDeposits.size());
    }

    private void givenDefaultUser() {
        User user = new User(VALID_EMAIL, VALID_PASSWORD, VALID_NAME, new DefaultUserValidator(),
                buildInitialEconomy());
        userRepository.add(user);

        this.owner = userRepository.findByEmail(VALID_EMAIL).get();
    }

    private void whenFindTermDepositsByOwnerId() {
        termDeposits = termDepositRepository.findActiveTermDepositsByOwnerId(owner.getId());
    }

    private void thenTermDepositsResultIsEmpty() {
        Assert.assertTrue(termDeposits.isEmpty());
    }

    private UserEconomy buildInitialEconomy() {
        return new UserEconomyFactory(INITIAL_COINS).buildInitialEconomy();
    }

}
