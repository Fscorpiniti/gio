package ar.edu.untref.gio.domain.interactor;

import ar.edu.untref.gio.domain.User;
import ar.edu.untref.gio.domain.UserEconomy;
import ar.edu.untref.gio.domain.UserRepository;
import ar.edu.untref.gio.domain.validator.DefaultUserValidator;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class FindUserInteractorTest {

    private static final String VALID_EMAIL = "test@gio.com";
    private static final String VALID_PASSWORD = "auth";
    private static final String VALID_NAME = "test";
    private static final Double INITIAL_COINS = new Double(1000);
    private static final Integer VALID_ID = 1;

    @Test
    public void whenFindExistentUserByIdThenReturnThisUser() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        User user = new User(VALID_EMAIL, VALID_PASSWORD, VALID_NAME, new DefaultUserValidator(),
                new UserEconomy(INITIAL_COINS));
        Mockito.when(userRepository.findById(VALID_ID)).thenReturn(Optional.of(user));

        FindUserInteractor findUserInteractor = new DefaultFindUserInteractor(userRepository);
        Optional<User> userOptional = findUserInteractor.findById(VALID_ID);

        Assert.assertTrue(userOptional.isPresent());
    }

}
