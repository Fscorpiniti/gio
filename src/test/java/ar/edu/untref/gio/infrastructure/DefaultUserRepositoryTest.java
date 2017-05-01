package ar.edu.untref.gio.infrastructure;

import ar.edu.untref.gio.domain.User;
import ar.edu.untref.gio.domain.UserEconomy;
import ar.edu.untref.gio.domain.UserEconomyFactory;
import ar.edu.untref.gio.domain.UserRepository;
import ar.edu.untref.gio.domain.validator.DefaultUserValidator;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context-test.xml"})
public class DefaultUserRepositoryTest {

    private static final String VALID_EMAIL = "test@gio.com";
    private static final String VALID_PASSWORD = "auth";
    private static final String VALID_NAME = "test";
    private static final Double INITIAL_COINS = new Double(1000);

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Resource(name = "defaultUserRepository")
    private UserRepository userRepository;

    @Test
    public void whenCreateUserThenUserIsStored() {
        givenCreateUserWithDefaultEconomy();
        Assert.assertTrue(userRepository.exist(VALID_EMAIL));
    }

    @Test
    public void whenEmailIsInexistentThenResultExistMethodIsFalse() {
        Assert.assertFalse(userRepository.exist(VALID_EMAIL));
    }

    @Test
    public void whenEmailIsNullThenExistMethodThrownException() {
        thrown.expect(IllegalArgumentException.class);
        Assert.assertFalse(userRepository.exist(null));
    }

    @Test
    public void whenEmailIsEmptyThenExistMethodThrownException() {
        thrown.expect(IllegalArgumentException.class);
        Assert.assertFalse(userRepository.exist(""));
    }

    @Test
    public void whenFindByEmailWithExistentEmailThenUserIsFound() {
        givenCreateUserWithDefaultEconomy();
        Assert.assertTrue(userRepository.findByEmail(VALID_EMAIL).isPresent());
    }

    @Test
    public void whenFindByEmailWithInExistentEmailThenResultIsEmpty() {
        Assert.assertFalse(userRepository.findByEmail(VALID_EMAIL).isPresent());
    }

    @Test
    public void whenCreateUserThenUserHasInitialEconomy(){
        givenCreateUserWithDefaultEconomy();

        Optional<User> userOptional = userRepository.findByEmail(VALID_EMAIL);
        Assert.assertNotNull(userOptional.get().getUserEconomy());
    }

    @Test
    public void whenCreateUserThenUserHasCorrectInitialCoins(){
        givenCreateUserWithDefaultEconomy();

        Optional<User> userOptional = userRepository.findByEmail(VALID_EMAIL);
        Assert.assertEquals(INITIAL_COINS, userOptional.get().getUserEconomy().getCoins());
    }

    private void givenCreateUserWithDefaultEconomy() {
        User user = new User(VALID_EMAIL, VALID_PASSWORD, VALID_NAME, new DefaultUserValidator(),
                buildInitialEconomy());
        userRepository.add(user);
    }

    private UserEconomy buildInitialEconomy() {
        return new UserEconomyFactory(INITIAL_COINS).buildInitialEconomy();
    }

}
