package ar.edu.untref.gio.persistence;

import ar.edu.untref.gio.domain.User;
import ar.edu.untref.gio.domain.UserRepository;
import ar.edu.untref.gio.validator.DefaultUserValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context-test.xml"})
public class RelationalUserRepositoryTest {

    private static final String VALID_EMAIL = "test@gio.com";
    private static final String VALID_PASSWORD = "auth";

    @Resource(name = "relationalUserRepository")
    private UserRepository userRepository;

    @Test
    public void whenCreateUserThenUserIsStored() {
        User user = new User(VALID_EMAIL, VALID_PASSWORD, new DefaultUserValidator());
        userRepository.add(user);
        Assert.assertTrue(userRepository.exist(user.getEmail()));
    }

    @Test
    public void whenEmailIsInexistentThenResultIsFalse() {
        Assert.assertFalse(userRepository.exist(VALID_EMAIL));
    }

}
