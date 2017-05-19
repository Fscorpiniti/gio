package ar.edu.untref.gio.domain.configuration;

import ar.edu.untref.gio.domain.TermDepositRepository;
import ar.edu.untref.gio.domain.UserRepository;
import ar.edu.untref.gio.domain.interactor.*;
import ar.edu.untref.gio.domain.service.ExpireTermDepositService;
import ar.edu.untref.gio.domain.service.UserCurrencyDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TermDepositConfiguration {

    @Bean
    public CreateTermDepositInteractor createTermDepositInteractor(TermDepositRepository termDepositRepository,
                                                                   FindUserInteractor findUserInteractor,
                                                                   UserCurrencyDomainService userCurrencyDomainService,
                                                                   UserRepository userRepository) {
        return new DefaultCreateTermDepositInteractor(termDepositRepository, findUserInteractor, userCurrencyDomainService,
                userRepository);
    }

    @Bean
    public FindTermDepositInteractor findTermDepositInteractor(TermDepositRepository termDepositRepository) {
        return new DefaultFindTermDepositInteractor(termDepositRepository);
    }

    @Bean
    public ForceTermDepositExpirationInteractor forceTermDepositExpirationInteractor(
            ExpireTermDepositService expireTermDepositService) {
        return new DefaultForceTermDepositInteractor(expireTermDepositService);
    }

}
