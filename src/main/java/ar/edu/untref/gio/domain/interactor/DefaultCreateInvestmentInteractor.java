package ar.edu.untref.gio.domain.interactor;

import ar.edu.untref.gio.domain.*;
import ar.edu.untref.gio.domain.service.UserCurrencyDomainService;
import ar.edu.untref.gio.infrastructure.exception.ObjectNotFoundException;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultCreateInvestmentInteractor implements CreateInvestmentInteractor {

    private InvestmentRepository investmentRepository;
    private UserCurrencyDomainService userCurrencyDomainService;
    private UserRepository userRepository;

    public DefaultCreateInvestmentInteractor(InvestmentRepository investmentRepository,
                                             UserCurrencyDomainService userCurrencyDomainService,
                                             UserRepository userRepository) {
        this.investmentRepository = investmentRepository;
        this.userCurrencyDomainService = userCurrencyDomainService;
        this.userRepository = userRepository;
    }

    @Override
    public List<Investment> execute(Integer ownerId, Integer investmentId) {
        Investment selected = getAllInvestments().stream()
                .filter(investment -> investment.getId().equals(investmentId))
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Inversion no encontrada"));

        investmentRepository.add(buildUserInvestment(ownerId, selected));

        userCurrencyDomainService.execute(buildUserCurrencyOperation(selected.getAmount()),
                userRepository.findById(ownerId));

        List<UserInvestment> userInvestments = investmentRepository.findByUserId(ownerId);
        Set<Integer> investmentIds = mapInvestmentIdToSet(userInvestments);

        return getAllInvestments().stream().filter(investment -> investmentIds.contains(investment.getId()))
                .collect(Collectors.toList());
    }

    private UserCurrencyOperation buildUserCurrencyOperation(Double amount) {
        return new DecrementUserCurrency(userRepository, amount);
    }

    private List<Investment> getAllInvestments() {
        return this.investmentRepository.getAll();
    }

    private Set<Integer> mapInvestmentIdToSet(List<UserInvestment> userInvestments) {
        return userInvestments.stream().map(userInvestment -> userInvestment.getInvestmentId()).collect(Collectors.toSet());
    }

    private UserInvestment buildUserInvestment(Integer ownerId, Investment selected) {
        return new UserInvestment(ownerId, selected.getId(), UserInvestmentStatus.ACTIVE, DateTime.now().toDate());
    }
}
