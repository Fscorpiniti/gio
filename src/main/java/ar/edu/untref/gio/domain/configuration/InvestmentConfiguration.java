package ar.edu.untref.gio.domain.configuration;

import ar.edu.untref.gio.domain.InvestmentRepository;
import ar.edu.untref.gio.domain.interactor.DefaultGetInvestmentInteractor;
import ar.edu.untref.gio.domain.interactor.GetInvestmentInteractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InvestmentConfiguration {

    @Bean
    public GetInvestmentInteractor getInvestmentInteractor(InvestmentRepository investmentRepository) {
        return new DefaultGetInvestmentInteractor(investmentRepository);
    }

}
