package ar.edu.untref.gio.action;

import ar.edu.untref.gio.domain.User;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CreateUserActionTest {

    public static final String VALID_EMAIL = "test@gio.com";

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void whenCreateValidUserThenUserIsNotNull() {
        String password = "auth";
        User user = new DefaultCreateUserAction().create(VALID_EMAIL, password);
        Assert.assertNotNull(user);
    }

    @Test
    public void whenCreateUserWithEmailAndPasswordThenUserContainsThisAttributes() {
        String password = "auth";
        User user = new DefaultCreateUserAction().create(VALID_EMAIL, password);
        Assert.assertEquals(VALID_EMAIL, user.getEmail());
        Assert.assertEquals(password, user.getPassword());
    }

    @Test
    public void whenCreateUserWithNullEmailThenExceptionIsThrown() {
        String email = null;
        String password = "auth";
        thrown.expect(IllegalArgumentException.class);
        new DefaultCreateUserAction().create(email, password);
    }

    @Test
    public void whenCreateUserWithEmptyEmailThenExceptionIsThrown() {
        String email = "";
        String password = "auth";
        thrown.expect(IllegalArgumentException.class);
        new DefaultCreateUserAction().create(email, password);
    }

    @Test
    public void whenCreateUserWithNullPasswordThenExceptionIsThrown() {
        String password = null;
        thrown.expect(IllegalArgumentException.class);
        new DefaultCreateUserAction().create(VALID_EMAIL, password);
    }

    @Test
    public void whenCreateUserWithEmptyPasswordThenExceptionIsThrown() {
        String password = "";
        thrown.expect(IllegalArgumentException.class);
        new DefaultCreateUserAction().create(VALID_EMAIL, password);
    }

}