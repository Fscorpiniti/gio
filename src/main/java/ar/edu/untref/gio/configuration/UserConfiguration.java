package ar.edu.untref.gio.configuration;


import ar.edu.untref.gio.action.CreateUserAction;
import ar.edu.untref.gio.action.DefaultCreateUserAction;
import ar.edu.untref.gio.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

    @Bean
    public CreateUserAction createUserAction(UserRepository userRepository) {
        return new DefaultCreateUserAction(userRepository);
    }

}
