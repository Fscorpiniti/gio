package ar.edu.untref.gio.persistence;

import ar.edu.untref.gio.domain.User;
import ar.edu.untref.gio.domain.UserRepository;
import ar.edu.untref.gio.validator.DefaultUserValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/application-context.xml"})
@Transactional
public class RelationalUserRepositoryTest {

    @Resource(name = "relationalUserRepository")
    private UserRepository userRepository;

    @Test
    public void whenCreateUserThenUserIsStored() {
        User user = new User("test@gio.com", "auth", new DefaultUserValidator());
        userRepository.add(user);
    }

}
