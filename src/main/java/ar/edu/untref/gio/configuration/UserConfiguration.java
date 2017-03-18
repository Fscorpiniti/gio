package ar.edu.untref.gio.configuration;


import ar.edu.untref.gio.controller.UserController;
import ar.edu.untref.gio.interactor.CreateUserInteractor;
import ar.edu.untref.gio.interactor.DefaultCreateUserInteractor;
import ar.edu.untref.gio.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class UserConfiguration {

    @Bean
    public CreateUserInteractor createUserInteractor(UserRepository userRepository) {
        return new DefaultCreateUserInteractor(userRepository);
    }

    @Bean
    @Scope("prototype")
    public UserController userController(CreateUserInteractor createUserInteractor) {
        return new UserController(createUserInteractor);
    }

}
