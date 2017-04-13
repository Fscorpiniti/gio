package ar.edu.untref.gio.domain.configuration;


import ar.edu.untref.gio.domain.interactor.CreateUserInteractor;
import ar.edu.untref.gio.domain.interactor.DefaultCreateUserInteractor;
import ar.edu.untref.gio.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

    @Bean
    public CreateUserInteractor createUserInteractor(UserRepository userRepository) {
        return new DefaultCreateUserInteractor(userRepository);
    }

}
