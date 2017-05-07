package ar.edu.untref.gio.domain.interactor;

import ar.edu.untref.gio.domain.*;
import ar.edu.untref.gio.domain.request.CreateTermDepositRequest;
import ar.edu.untref.gio.domain.service.DefaultUserCurrencyDomainService;
import ar.edu.untref.gio.domain.service.UserCurrencyDomainService;
import ar.edu.untref.gio.domain.validator.DefaultUserValidator;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class FindTermDepositInteractorTest {

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    private List<TermDeposit> termDeposits;
    private TermDepositRepository termDepositRepository = Mockito.mock(TermDepositRepository.class);
    private TermDepositInformation termDepositInformation;

    private static final Double VALID_BI_MONTHLY_RATE = new Double(15);
    private static final Double VALID_QUARTERLY_RATE = new Double(20);
    private static final Double VALID_SEMIANNUAL_RATE = new Double(25);
    private static final Double VALID_ANNUAL_RATE = new Double(30);
    private static final Double VALID_MONTHLY_RATE = new Double(10);
    private static final int DEFAULT_DURATION = 30;
    private static final int DEFAULT_AMOUNT = 100;
    private static final int DEFAULT_RATE = 15;

    private User owner;
    private static final String VALID_EMAIL = "test@gio.com";
    private static final String VALID_PASSWORD = "auth";
    private static final String VALID_NAME = "test";
    private static final Double INITIAL_COINS = new Double(1000);
    private static final int VALID_ID = 1;

    @Before
    public void setUp() {
        owner = new User(VALID_EMAIL, VALID_PASSWORD, VALID_NAME, new DefaultUserValidator(), buildInitialEconomy(),
                VALID_ID);
    }

    @Test
    public void whenFindTermDepositsWithNullOwnerIdThenExceptionIsThrown() {
        Integer ownerId = null;

        thrown.expect(NullPointerException.class);
        whenFindTermDepositsByOwnerId(ownerId);
    }

    @Test
    public void whenFindTermDepositsWithoutTermDepositsCreatedThenResultIsEmpty() {
        whenFindTermDepositsByOwnerId(VALID_ID);

        thenTermDepositsResultIsEmpty();
    }

    @Test
    public void whenFindTermDepositsWithTermDepositsCreatedThenResultContainsElements() {
        givenCreateTermDeposit();

        whenFindTermDepositsByOwnerId(VALID_ID);

        thenTermDepositsResultContainsElements();
    }

    @Test
    public void whenFindTermDepositInformationThenInformationIsReturned() {
        whenFindTermDepositInformation();

        thenTermDepositInformationIsNotNull();
    }

    @Test
    public void whenFindTermDepositInformationWithRatesThenRatesAreCorrect() {
        whenFindTermDepositInformation();

        thenRatesAreCorrect();
    }

    private void thenRatesAreCorrect() {
        Assert.assertEquals(VALID_MONTHLY_RATE, termDepositInformation.getMonthlyRate());
        Assert.assertEquals(VALID_BI_MONTHLY_RATE, termDepositInformation.getBiMonthlyRate());
        Assert.assertEquals(VALID_QUARTERLY_RATE, termDepositInformation.getQuarterlyRate());
        Assert.assertEquals(VALID_SEMIANNUAL_RATE, termDepositInformation.getSemiAnnualRate());
        Assert.assertEquals(VALID_ANNUAL_RATE, termDepositInformation.getAnnualRate());
    }

    private void thenTermDepositInformationIsNotNull() {
        Assert.assertNotNull(termDepositInformation);
    }

    private void whenFindTermDepositInformation() {
        Mockito.when(termDepositRepository.findTermDepositInformationForCreation()).thenReturn(new TermDepositInformation(VALID_MONTHLY_RATE,
                VALID_BI_MONTHLY_RATE, VALID_QUARTERLY_RATE, VALID_SEMIANNUAL_RATE, VALID_ANNUAL_RATE));
        termDepositInformation = new DefaultFindTermDepositInteractor(termDepositRepository).findTermDepositInformationForCreation();
    }

    private void thenTermDepositsResultContainsElements() {
        Assert.assertEquals(1, termDeposits.size());
    }

    private void givenCreateTermDeposit() {
        Integer duration = new Integer(DEFAULT_DURATION);
        Double amount = new Double(DEFAULT_AMOUNT);
        Double rate = new Double(DEFAULT_RATE);
        CreateTermDepositRequest createTermDepositRequest = new CreateTermDepositRequest(amount,
                rate, duration);
        FindUserInteractor findUserInteractor = Mockito.mock(FindUserInteractor.class);
        Mockito.when(findUserInteractor.findById(VALID_ID)).thenReturn(Optional.of(owner));
        UserCurrencyDomainService userCurrencyDomainService = new DefaultUserCurrencyDomainService();
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        TermDeposit termDeposit = new DefaultCreateTermDepositInteractor(termDepositRepository, findUserInteractor,
                userCurrencyDomainService, userRepository)
                .create(createTermDepositRequest, owner.getId());
        Mockito.when(termDepositRepository.findActiveTermDepositsByOwnerId(owner.getId())).thenReturn(Arrays.asList(termDeposit));
    }

    private void thenTermDepositsResultIsEmpty() {
        Assert.assertTrue(termDeposits.isEmpty());
    }

    private void whenFindTermDepositsByOwnerId(Integer ownerId) {
        termDeposits = new DefaultFindTermDepositInteractor(termDepositRepository).findByOwnerId(ownerId);
    }

    private UserEconomy buildInitialEconomy() {
        return new UserEconomyFactory(INITIAL_COINS).buildInitialEconomy();
    }

}
