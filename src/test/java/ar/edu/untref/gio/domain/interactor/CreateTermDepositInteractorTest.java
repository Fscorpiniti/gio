package ar.edu.untref.gio.domain.interactor;

import ar.edu.untref.gio.domain.*;
import ar.edu.untref.gio.domain.exception.InsufficientCurrenciesException;
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

import java.util.Date;
import java.util.Optional;

public class CreateTermDepositInteractorTest {

    private static final Double INSUFFICIENT_CURRENCIES = new Double(2000);

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    private CreateTermDepositRequest createTermDepositRequest;
    private TermDeposit termDeposit;
    private Double validAmount = new Double(100);
    private Double validRate = new Double(15);
    private Integer validDuration = 30;
    private User owner;
    private static final String VALID_EMAIL = "test@gio.com";
    private static final String VALID_PASSWORD = "auth";
    private static final String VALID_NAME = "test";
    private static final Double INITIAL_COINS = new Double(1000);
    private static final int VALID_ID = 1;
    private static final Integer ONE_DAY_BEFORE_LIMIT_VALID = 29;

    @Before
    public void setUp() {
        owner = new User(VALID_EMAIL, VALID_PASSWORD, VALID_NAME, new DefaultUserValidator(), buildInitialEconomy(),
                VALID_ID);
    }

    @Test
    public void whenCreateTermDepositThenTermDepositIsCreated() {
        givenTermDepositRequestWith(validAmount, validRate, validDuration);

        whenCreateTermDeposit();

        thenTermDepositIsCreated();
    }

    @Test
    public void whenCreateTermDepositWithNullAmountThenExceptionIsThrown() {
        givenTermDepositRequestWith(null, validRate, validDuration);

        thrown.expect(NullPointerException.class);
        whenCreateTermDeposit();
    }

    @Test
    public void whenCreateTermDepositWithNullRateThenExceptionIsThrown() {
        givenTermDepositRequestWith(validAmount, null, validDuration);

        thrown.expect(NullPointerException.class);
        whenCreateTermDeposit();
    }

    @Test
    public void whenCreateTermDepositWithNullDurationThenExceptionIsThrown() {
        givenTermDepositRequestWith(validAmount, validRate, null);

        thrown.expect(NullPointerException.class);
        whenCreateTermDeposit();
    }

    @Test
    public void whenCreateTermDepositThenTermDepositIsActive() {
        givenTermDepositRequestWith(validAmount, validRate, validDuration);

        whenCreateTermDeposit();

        thenTermDepositIsActive();
    }

    @Test
    public void whenCreateTermDepositThenTermDepositContainsCorrectAmount() {
        givenTermDepositRequestWith(validAmount, validRate, validDuration);

        whenCreateTermDeposit();

        thenTermDepositContainsCorrectAmount();
    }

    @Test
    public void whenCreateTermDepositThenTermDepositContainsCorrectRate() {
        givenTermDepositRequestWith(validAmount, validRate, validDuration);

        whenCreateTermDeposit();

        thenTermDepositContainsCorrectRate();
    }

    @Test
    public void whenCreateTermDepositThenTermDepositContainsCorrectExpirationDate() {
        givenTermDepositRequestWith(validAmount, validRate, validDuration);

        whenCreateTermDeposit();

        thenTermDepositContainsCorrectExpirationRate();
    }

    @Test
    public void whenCreateTermDepositWithInvalidDurationThenExceptionIsThrown() {
        givenTermDepositRequestWith(validAmount, validRate, ONE_DAY_BEFORE_LIMIT_VALID);

        thrown.expect(IllegalArgumentException.class);
        whenCreateTermDeposit();
    }

    @Test
    public void whenCreateTermDepositInteractorWithNullRepositoryThenExceptionIsThrown() {
        FindUserInteractor findUserInteractor = Mockito.mock(FindUserInteractor.class);
        UserCurrencyDomainService userCurrencyDomainService = new DefaultUserCurrencyDomainService();
        UserRepository userRepository = Mockito.mock(UserRepository.class);

        thrown.expect(NullPointerException.class);
        new DefaultCreateTermDepositInteractor(null, findUserInteractor, userCurrencyDomainService,
                userRepository);
    }

    @Test
    public void whenCreateTermDepositWithInsufficientCurrenciesThenExceptionIsThrown() {
        givenTermDepositRequestWith(INSUFFICIENT_CURRENCIES, validRate, validDuration);

        TermDepositRepository termDepositRepository = Mockito.mock(TermDepositRepository.class);
        FindUserInteractor findUserInteractor = Mockito.mock(FindUserInteractor.class);
        Mockito.when(findUserInteractor.findById(VALID_ID)).thenReturn(Optional.of(owner));
        UserCurrencyDomainService userCurrencyDomainService = new DefaultUserCurrencyDomainService();
        UserRepository userRepository = Mockito.mock(UserRepository.class);

        thrown.expect(InsufficientCurrenciesException.class);
        termDeposit = new DefaultCreateTermDepositInteractor(termDepositRepository, findUserInteractor,
                userCurrencyDomainService, userRepository).create(createTermDepositRequest, VALID_ID);
    }

    private void thenTermDepositContainsCorrectExpirationRate() {
        Assert.assertEquals(createTermDepositRequest.getExpiration(), termDeposit.getExpiration());
    }

    private void thenTermDepositContainsCorrectRate() {
        Assert.assertEquals(validRate, termDeposit.getRate());
    }

    private void thenTermDepositContainsCorrectAmount() {
        Assert.assertEquals(validAmount, termDeposit.getAmount());
    }

    private void thenTermDepositIsActive() {
        Assert.assertEquals(TermDepositStatus.ACTIVE, termDeposit.getStatus());
    }

    private void givenTermDepositRequestWith(Double amount, Double rate, Integer duration) {
        createTermDepositRequest = new CreateTermDepositRequest(amount, rate, duration);
    }

    private void whenCreateTermDeposit() {
        TermDepositRepository termDepositRepository = Mockito.mock(TermDepositRepository.class);
        FindUserInteractor findUserInteractor = Mockito.mock(FindUserInteractor.class);
        Mockito.when(findUserInteractor.findById(VALID_ID)).thenReturn(Optional.of(owner));
        UserCurrencyDomainService userCurrencyDomainService = new DefaultUserCurrencyDomainService();
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        termDeposit = new DefaultCreateTermDepositInteractor(termDepositRepository, findUserInteractor,
                userCurrencyDomainService, userRepository).create(createTermDepositRequest, VALID_ID);
    }

    private void thenTermDepositIsCreated() {
        Assert.assertNotNull(termDeposit);
    }

    private UserEconomy buildInitialEconomy() {
        return new UserEconomyFactory(INITIAL_COINS).buildInitialEconomy();
    }

}
