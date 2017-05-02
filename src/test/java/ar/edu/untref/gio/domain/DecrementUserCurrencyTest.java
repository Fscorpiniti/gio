package ar.edu.untref.gio.domain;

import ar.edu.untref.gio.domain.exception.InsufficientCurrenciesException;
import ar.edu.untref.gio.domain.validator.DefaultUserValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

public class DecrementUserCurrencyTest {

    private static final Double AMOUNT_INSUFFICIENT = new Double(2000);
    private User owner;
    private static final String VALID_EMAIL = "test@gio.com";
    private static final String VALID_PASSWORD = "auth";
    private static final String VALID_NAME = "test";
    private static final Double INITIAL_COINS = new Double(1000);
    private static final int VALID_ID = 1;
    private static final Double VALID_AMOUNT_CURRENCY = new Double(300);

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    private UserCurrencyOperation userCurrencyOperation;

    @Before
    public void setUp() {
        owner = new User(VALID_EMAIL, VALID_PASSWORD, VALID_NAME, new DefaultUserValidator(), buildInitialEconomy(),
                VALID_ID);
    }

    @Test
    public void whenDecrementUserCurrencyWithInsufficientCurrenciesThenExceptionIsThrown() {
        givenOperationWithAmount(AMOUNT_INSUFFICIENT);

        thrown.expect(InsufficientCurrenciesException.class);
        whenExecuteOperation();
    }

    @Test
    public void whenDecrementUserCurrencyWithSufficientCurrenciesThenExceptionIsThrown() {
        givenOperationWithAmount(VALID_AMOUNT_CURRENCY);

        User userAfterProcess = whenExecuteOperation();

        thenCurrenciesAreCorrect(userAfterProcess);
    }

    private User whenExecuteOperation() {
        return userCurrencyOperation.execute(owner);
    }

    private void thenCurrenciesAreCorrect(User userAfterProcess) {
        Double expectedCurrencies = new Double(700);
        Assert.assertEquals(expectedCurrencies, userAfterProcess.getUserEconomy().getCoins());
    }

    private void givenOperationWithAmount(Double amount) {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        userCurrencyOperation = new DecrementUserCurrency(userRepository, amount);
    }

    private UserEconomy buildInitialEconomy() {
        return new UserEconomyFactory(INITIAL_COINS).buildInitialEconomy();
    }

}
