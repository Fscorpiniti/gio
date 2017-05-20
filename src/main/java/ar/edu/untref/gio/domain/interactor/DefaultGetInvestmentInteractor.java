package ar.edu.untref.gio.domain.interactor;

import ar.edu.untref.gio.domain.Investment;
import ar.edu.untref.gio.domain.InvestmentRepository;
import ar.edu.untref.gio.domain.UserInvestment;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultGetInvestmentInteractor implements GetInvestmentInteractor {

    private InvestmentRepository investmentRepository;

    public DefaultGetInvestmentInteractor(InvestmentRepository investmentRepository) {
        this.investmentRepository = investmentRepository;
    }

    @Override
    public List<Investment> getAll(Integer ownerId) {
        List<Investment> all = investmentRepository.getAll();
        List<UserInvestment> userInvestments = investmentRepository.findByUserId(ownerId);
        Set<Integer> investmentIds = mapOwnerId(userInvestments);
        return all.stream().filter(investment -> investmentIds.contains(investment.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Investment> getByOwnerId(Integer ownerId) {
        List<Investment> all = investmentRepository.getAll();
        List<UserInvestment> userInvestments = investmentRepository.findByUserId(ownerId);
        Set<Integer> investmentIds = mapOwnerId(userInvestments);
        return all.stream().filter(investment -> !investmentIds.contains(investment.getId()))
                .collect(Collectors.toList());
    }

    private Set<Integer> mapOwnerId(List<UserInvestment> userInvestments) {
        return userInvestments.stream().map(userInvestment -> userInvestment.getOwnerId())
                .collect(Collectors.toSet());
    }
}
