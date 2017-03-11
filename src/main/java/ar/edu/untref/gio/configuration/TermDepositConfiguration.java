package ar.edu.untref.gio.configuration;

import ar.edu.untref.gio.domain.TermDepositRepository;
import ar.edu.untref.gio.interactor.CreateTermDepositInteractor;
import ar.edu.untref.gio.interactor.DefaultCreateTermDepositInteractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TermDepositConfiguration {

    @Bean
    public CreateTermDepositInteractor createTermDepositInteractor(TermDepositRepository termDepositRepository) {
        return new DefaultCreateTermDepositInteractor(termDepositRepository);
    }

}
