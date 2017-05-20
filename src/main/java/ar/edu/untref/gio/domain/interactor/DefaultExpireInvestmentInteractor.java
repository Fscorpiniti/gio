package ar.edu.untref.gio.domain.interactor;

import ar.edu.untref.gio.domain.*;
import ar.edu.untref.gio.domain.service.UserCurrencyDomainService;

public class DefaultExpireInvestmentInteractor implements ExpireInvestmentInteractor {

    private InvestmentRepository investmentRepository;
    private UserCurrencyDomainService userCurrencyDomainService;
    private UserRepository userRepository;

    public DefaultExpireInvestmentInteractor(InvestmentRepository investmentRepository,
                                             UserCurrencyDomainService userCurrencyDomainService,
                                                UserRepository userRepository) {
        this.investmentRepository = investmentRepository;
        this.userCurrencyDomainService = userCurrencyDomainService;
        this.userRepository = userRepository;
    }

    @Override
    public void expire(Integer ownerId, Integer investmentId) {
        Investment investmentToExpired = investmentRepository.getAll().stream()
                .filter(investment -> investment.getId().equals(investmentId)).findFirst().get();

        UserInvestment userInvestmentToExpire = investmentRepository.findByUserId(ownerId)
                .stream().filter(userInvestment -> userInvestment.getInvestmentId().equals(investmentId))
                .findFirst().get();

        userInvestmentToExpire.finalize();
        investmentRepository.add(userInvestmentToExpire);
        userCurrencyDomainService.execute(buildUserCurrencyOperation(investmentToExpired),
                userRepository.findById(ownerId));
    }

    private IncrementUserCurrency buildUserCurrencyOperation(Investment investmentToExpired) {
        return new IncrementUserCurrency(userRepository, investmentToExpired.calculateValueToBelieve());
    }
}
