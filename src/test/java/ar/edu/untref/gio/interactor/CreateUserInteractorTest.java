package ar.edu.untref.gio.interactor;

import ar.edu.untref.gio.domain.User;
import ar.edu.untref.gio.domain.UserRepository;
import ar.edu.untref.gio.exception.EmailAlreadyExistentException;
import ar.edu.untref.gio.request.CreateUserRequest;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

public class CreateUserInteractorTest {

    private static final String VALID_EMAIL = "test@gio.com";
    private static final String VALID_PASSWORD = "auth";
    private static final String VALID_NAME = "test";

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void whenCreateValidUserThenUserIsNotNull() {
        UserRepository userRepository = getUserRepositoryMock();
        Mockito.when(userRepository.exist(VALID_EMAIL)).thenReturn(Boolean.FALSE);
        User user = new DefaultCreateUserInteractor(userRepository).create(
                createUserRequest(VALID_EMAIL, VALID_PASSWORD, VALID_NAME));
        Assert.assertNotNull(user);
    }

    @Test
    public void whenCreateUserWithEmailAndPasswordThenUserContainsThisAttributes() {
        UserRepository userRepository = getUserRepositoryMock();
        Mockito.when(userRepository.exist(VALID_EMAIL)).thenReturn(Boolean.FALSE);
        User user = new DefaultCreateUserInteractor(userRepository).create(
                createUserRequest(VALID_EMAIL, VALID_PASSWORD, VALID_NAME));
        Assert.assertEquals(VALID_EMAIL, user.getEmail());
        Assert.assertEquals(VALID_PASSWORD, user.getPassword());
        Assert.assertEquals(VALID_NAME, user.getName());
    }

    @Test
    public void whenCreateUserWithNullEmailThenExceptionIsThrown() {
        String email = null;
        thrown.expect(IllegalArgumentException.class);
        new DefaultCreateUserInteractor(getUserRepositoryMock()).create(createUserRequest(email, VALID_PASSWORD, VALID_NAME));
    }

    @Test
    public void whenCreateUserWithEmptyEmailThenExceptionIsThrown() {
        String email = StringUtils.EMPTY;
        thrown.expect(IllegalArgumentException.class);
        new DefaultCreateUserInteractor(getUserRepositoryMock()).create(createUserRequest(email, VALID_PASSWORD, VALID_NAME));
    }

    @Test
    public void whenCreateUserWithNullPasswordThenExceptionIsThrown() {
        String password = null;
        thrown.expect(IllegalArgumentException.class);
        new DefaultCreateUserInteractor(getUserRepositoryMock()).create(createUserRequest(VALID_EMAIL, password, VALID_NAME));
    }

    @Test
    public void whenCreateUserWithEmptyPasswordThenExceptionIsThrown() {
        String password = StringUtils.EMPTY;
        thrown.expect(IllegalArgumentException.class);
        new DefaultCreateUserInteractor(getUserRepositoryMock()).create(createUserRequest(VALID_EMAIL, password, VALID_NAME));
    }

    @Test
    public void whenCreateUserWithRepeatEmailThenExceptionIsThrown() {
        UserRepository userRepository = getUserRepositoryMock();
        Mockito.when(userRepository.exist(VALID_EMAIL)).thenReturn(Boolean.TRUE);
        thrown.expect(EmailAlreadyExistentException.class);
        new DefaultCreateUserInteractor(userRepository).create(createUserRequest(VALID_EMAIL, VALID_PASSWORD, VALID_NAME));
    }

    @Test
    public void whenCreateUserWithNullNameThenExceptionIsThrown() {
        String name = null;
        thrown.expect(IllegalArgumentException.class);
        new DefaultCreateUserInteractor(getUserRepositoryMock()).create(createUserRequest(VALID_EMAIL, VALID_PASSWORD, name));
    }

    @Test
    public void whenCreateUserWithEmptyNameThenExceptionIsThrown() {
        String name = StringUtils.EMPTY;
        thrown.expect(IllegalArgumentException.class);
        new DefaultCreateUserInteractor(getUserRepositoryMock()).create(createUserRequest(VALID_EMAIL, VALID_PASSWORD, name));
    }

    @Test
    public void whenCreateUserInteractorWithNullRepositoryThenExceptionIsThrown() {
        thrown.expect(NullPointerException.class);
        new DefaultCreateUserInteractor(null);
    }

    private UserRepository getUserRepositoryMock() {
        return Mockito.mock(UserRepository.class);
    }

    private CreateUserRequest createUserRequest(String email, String password, String name) {
        return new CreateUserRequest(email, password, name);
    }

}