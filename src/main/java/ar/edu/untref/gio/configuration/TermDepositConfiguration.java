package ar.edu.untref.gio.configuration;

import ar.edu.untref.gio.domain.TermDepositRepository;
import ar.edu.untref.gio.interactor.CreateTermDepositInteractor;
import ar.edu.untref.gio.interactor.DefaultCreateTermDepositInteractor;
import ar.edu.untref.gio.interactor.DefaultFindTermDepositInteractor;
import ar.edu.untref.gio.interactor.FindTermDepositInteractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TermDepositConfiguration {

    @Bean
    public CreateTermDepositInteractor createTermDepositInteractor(TermDepositRepository termDepositRepository) {
        return new DefaultCreateTermDepositInteractor(termDepositRepository);
    }

    @Bean
    public FindTermDepositInteractor findTermDepositInteractor(TermDepositRepository termDepositRepository) {
        return new DefaultFindTermDepositInteractor(termDepositRepository);
    }

}
