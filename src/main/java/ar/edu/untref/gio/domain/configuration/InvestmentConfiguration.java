package ar.edu.untref.gio.domain.configuration;

import ar.edu.untref.gio.domain.InvestmentRepository;
import ar.edu.untref.gio.domain.UserRepository;
import ar.edu.untref.gio.domain.interactor.*;
import ar.edu.untref.gio.domain.service.UserCurrencyDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InvestmentConfiguration {

    @Bean
    public GetInvestmentInteractor getInvestmentInteractor(InvestmentRepository investmentRepository) {
        return new DefaultGetInvestmentInteractor(investmentRepository);
    }

    @Bean
    public CreateInvestmentInteractor createInvestmentInteractor(InvestmentRepository investmentRepository) {
        return new DefaultCreateInvestmentInteractor(investmentRepository);
    }

    @Bean
    public ExpireInvestmentInteractor expireInvestmentInteractor(InvestmentRepository investmentRepository,
                                                                 UserCurrencyDomainService userCurrencyDomainService,
                                                                 UserRepository userRepository) {
        return new DefaultExpireInvestmentInteractor(investmentRepository, userCurrencyDomainService, userRepository);
    }

}
