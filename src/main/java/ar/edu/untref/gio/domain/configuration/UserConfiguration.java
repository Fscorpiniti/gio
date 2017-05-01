package ar.edu.untref.gio.domain.configuration;


import ar.edu.untref.gio.domain.interactor.CreateUserInteractor;
import ar.edu.untref.gio.domain.interactor.DefaultCreateUserInteractor;
import ar.edu.untref.gio.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Properties;

@Configuration
public class UserConfiguration {

    private static final String INITIAL_COINS = "initial_coins";

    @Resource(name = "props")
    private Properties properties;

    @Bean
    public CreateUserInteractor createUserInteractor(UserRepository userRepository) {
        Double initialCoins = Double.valueOf(properties.getProperty(INITIAL_COINS));
        return new DefaultCreateUserInteractor(userRepository, initialCoins);
    }

}
