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
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context-test.xml"})
@WebAppConfiguration
public class DefaultUserRepositoryTest {

    private static final String VALID_EMAIL = "test@gio.com";
    private static final String VALID_PASSWORD = "auth";
    private static final String VALID_NAME = "test";
    private static final Double INITIAL_COINS = new Double(1000);

    private User user;

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Resource(name = "defaultUserRepository")
    private UserRepository userRepository;

    @Test
    public void whenCreateUserThenUserIsStored() {
        givenUserWithDefaultEconomy();
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
        givenUserWithDefaultEconomy();
        Assert.assertTrue(findByEmail().isPresent());
    }

    @Test
    public void whenFindByEmailWithInExistentEmailThenResultIsEmpty() {
        Assert.assertFalse(findByEmail().isPresent());
    }

    @Test
    public void whenCreateUserThenUserHasInitialEconomy(){
        givenUserWithDefaultEconomy();

        Optional<User> userOptional = findByEmail();

        thenEconomyIsNotNull(userOptional);
    }

    @Test
    public void whenCreateUserThenUserHasCorrectInitialCoins(){
        givenUserWithDefaultEconomy();

        Optional<User> userOptional = findByEmail();

        thenEconomyContainsCorrectCoins(userOptional);
    }

    @Test
    public void whenFindInexistentUserByIdThenResultIsEmpty(){
        givenUserWithDefaultEconomy();

        int inexistentId = 100;
        Optional<User> userOptional = findById(inexistentId);

        thenResultUserIsEmpty(userOptional);
    }

    @Test
    public void whenFindValidUserByIdThenResultContainsThisUser(){
        givenUserWithDefaultEconomy();

        Optional<User> byEmail = findByEmail();
        Optional<User> userOptional = findById(byEmail.get().getId());

        thenResultUserIsNotEmpty(userOptional);
    }

    private void thenResultUserIsNotEmpty(Optional<User> userOptional) {
        Assert.assertTrue(userOptional.isPresent());
    }

    private void thenResultUserIsEmpty(Optional<User> userOptional) {
        Assert.assertFalse(userOptional.isPresent());
    }

    private Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    private void thenEconomyContainsCorrectCoins(Optional<User> userOptional) {
        Assert.assertEquals(INITIAL_COINS, userOptional.get().getUserEconomy().getCoins());
    }

    private void thenEconomyIsNotNull(Optional<User> userOptional) {
        Assert.assertNotNull(userOptional.get().getUserEconomy());
    }

    private Optional<User> findByEmail() {
        return userRepository.findByEmail(VALID_EMAIL);
    }

    private void givenUserWithDefaultEconomy() {
        User user = new User(VALID_EMAIL, VALID_PASSWORD, VALID_NAME, new DefaultUserValidator(),
                buildInitialEconomy());
        userRepository.add(user);
    }

    private UserEconomy buildInitialEconomy() {
        return new UserEconomyFactory(INITIAL_COINS).buildInitialEconomy();
    }

}
