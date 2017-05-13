package ar.edu.untref.gio.domain;

import ar.edu.untref.gio.domain.validator.DefaultUserValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

public class IncrementUserCurrencyTest {

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
    public void whenDecrementUserCurrencyWithSufficientCurrenciesThenUserIsUpdated() {
        givenOperationWithAmount(VALID_AMOUNT_CURRENCY);

        User userAfterProcess = whenExecuteOperation();

        thenCurrenciesAreCorrect(userAfterProcess);
    }

    private User whenExecuteOperation() {
        return userCurrencyOperation.execute(owner);
    }

    private void thenCurrenciesAreCorrect(User userAfterProcess) {
        Double expectedCurrencies = new Double(1300);
        Assert.assertEquals(expectedCurrencies, userAfterProcess.getUserEconomy().getCoins());
    }

    private void givenOperationWithAmount(Double amount) {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        userCurrencyOperation = new IncrementUserCurrency(userRepository, amount);
    }

    private UserEconomy buildInitialEconomy() {
        return new UserEconomyFactory(INITIAL_COINS).buildInitialEconomy();
    }

}
