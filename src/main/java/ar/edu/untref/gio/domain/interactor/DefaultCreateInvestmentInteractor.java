package ar.edu.untref.gio.domain.interactor;

import ar.edu.untref.gio.domain.Investment;
import ar.edu.untref.gio.domain.InvestmentRepository;
import ar.edu.untref.gio.domain.UserInvestment;
import ar.edu.untref.gio.domain.UserInvestmentStatus;
import ar.edu.untref.gio.infrastructure.exception.ObjectNotFoundException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultCreateInvestmentInteractor implements CreateInvestmentInteractor {

    private InvestmentRepository investmentRepository;

    public DefaultCreateInvestmentInteractor(InvestmentRepository investmentRepository) {
        this.investmentRepository = investmentRepository;
    }

    @Override
    public List<Investment> execute(Integer ownerId, Integer investmentId) {
        Investment selected = getAllInvestments().stream()
                .filter(investment -> investment.getId().equals(investmentId))
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Inversion no encontrada"));

        investmentRepository.add(buildUserInvestment(ownerId, selected));

        List<UserInvestment> userInvestments = investmentRepository.findByUserId(ownerId);
        Set<Integer> investmentIds = mapOwnerIdToSet(userInvestments);

        return getAllInvestments().stream().filter(investment -> investmentIds.contains(investment.getId()))
                .collect(Collectors.toList());
    }

    private List<Investment> getAllInvestments() {
        return this.investmentRepository.getAll();
    }

    private Set<Integer> mapOwnerIdToSet(List<UserInvestment> userInvestments) {
        return userInvestments.stream().map(userInvestment -> userInvestment.getOwnerId()).collect(Collectors.toSet());
    }

    private UserInvestment buildUserInvestment(Integer ownerId, Investment selected) {
        return new UserInvestment(ownerId, selected.getId(), UserInvestmentStatus.ACTIVE);
    }
}
