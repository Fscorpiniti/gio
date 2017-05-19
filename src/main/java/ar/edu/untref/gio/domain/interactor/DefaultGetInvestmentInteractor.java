package ar.edu.untref.gio.domain.interactor;

import ar.edu.untref.gio.domain.Investment;
import ar.edu.untref.gio.domain.InvestmentRepository;

import java.util.List;

public class DefaultGetInvestmentInteractor implements GetInvestmentInteractor {

    private InvestmentRepository investmentRepository;

    public DefaultGetInvestmentInteractor(InvestmentRepository investmentRepository) {
        this.investmentRepository = investmentRepository;
    }

    @Override
    public List<Investment> getAll() {
        return investmentRepository.getAll();
    }
}
