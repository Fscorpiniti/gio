package ar.edu.untref.gio.action;

import ar.edu.untref.gio.domain.User;
import ar.edu.untref.gio.domain.UserRepository;
import ar.edu.untref.gio.exception.EmailAlreadyExistentException;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

public class CreateUserActionTest {

    private static final String VALID_EMAIL = "test@gio.com";
    private static final String VALID_PASSWORD = "auth";

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void whenCreateValidUserThenUserIsNotNull() {
        UserRepository userRepository = getUserRepositoryMock();
        Mockito.when(userRepository.exist(VALID_EMAIL)).thenReturn(Boolean.FALSE);
        User user = new DefaultCreateUserAction(userRepository).create(VALID_EMAIL, VALID_PASSWORD);
        Assert.assertNotNull(user);
    }

    @Test
    public void whenCreateUserWithEmailAndPasswordThenUserContainsThisAttributes() {
        UserRepository userRepository = getUserRepositoryMock();
        Mockito.when(userRepository.exist(VALID_EMAIL)).thenReturn(Boolean.FALSE);
        User user = new DefaultCreateUserAction(userRepository).create(VALID_EMAIL, VALID_PASSWORD);
        Assert.assertEquals(VALID_EMAIL, user.getEmail());
        Assert.assertEquals(VALID_PASSWORD, user.getPassword());
    }

    @Test
    public void whenCreateUserWithNullEmailThenExceptionIsThrown() {
        String email = null;
        thrown.expect(IllegalArgumentException.class);
        new DefaultCreateUserAction(getUserRepositoryMock()).create(email, VALID_PASSWORD);
    }

    @Test
    public void whenCreateUserWithEmptyEmailThenExceptionIsThrown() {
        String email = StringUtils.EMPTY;
        thrown.expect(IllegalArgumentException.class);
        new DefaultCreateUserAction(getUserRepositoryMock()).create(email, VALID_PASSWORD);
    }

    @Test
    public void whenCreateUserWithNullPasswordThenExceptionIsThrown() {
        String password = null;
        thrown.expect(IllegalArgumentException.class);
        new DefaultCreateUserAction(getUserRepositoryMock()).create(VALID_EMAIL, password);
    }

    @Test
    public void whenCreateUserWithEmptyPasswordThenExceptionIsThrown() {
        String password = StringUtils.EMPTY;
        thrown.expect(IllegalArgumentException.class);
        new DefaultCreateUserAction(getUserRepositoryMock()).create(VALID_EMAIL, password);
    }

    @Test
    public void whenCreateUserWithRepeatEmailThenExceptionIsThrown() {
        UserRepository userRepository = getUserRepositoryMock();
        Mockito.when(userRepository.exist(VALID_EMAIL)).thenReturn(Boolean.TRUE);
        thrown.expect(EmailAlreadyExistentException.class);
        new DefaultCreateUserAction(userRepository).create(VALID_EMAIL, VALID_PASSWORD);
    }

    @Test
    public void whenCreateUserActionWithNullRepositoryThenExceptionIsThrown() {
        thrown.expect(IllegalArgumentException.class);
        new DefaultCreateUserAction(null);
    }

    private UserRepository getUserRepositoryMock() {
        return Mockito.mock(UserRepository.class);
    }

}