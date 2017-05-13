package ar.edu.untref.gio.domain.configuration;

import ar.edu.untref.gio.domain.TermDepositRepository;
import ar.edu.untref.gio.domain.UserRepository;
import ar.edu.untref.gio.domain.service.DefaultExpireTermDepositService;
import ar.edu.untref.gio.domain.service.ExpireTermDepositService;
import ar.edu.untref.gio.domain.service.UserCurrencyDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExpireTermDepositConfiguration {

    @Bean
    public ExpireTermDepositService expireTermDepositService(TermDepositRepository termDepositRepository,
                                                             UserCurrencyDomainService userCurrencyDomainService,
                                                             UserRepository userRepository) {
        return new DefaultExpireTermDepositService(termDepositRepository, userCurrencyDomainService, userRepository);
    }

}
