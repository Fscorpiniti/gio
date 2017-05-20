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
    public Double expire(Integer ownerId, Integer investmentId) {
        Investment investmentToExpired = investmentRepository.getAll().stream()
                .filter(investment -> investment.getId().equals(investmentId)).findFirst().get();

        UserInvestment userInvestmentToExpire = investmentRepository.findByUserId(ownerId)
                .stream().filter(userInvestment -> userInvestment.getInvestmentId().equals(investmentId))
                .findFirst().get();

        userInvestmentToExpire.finalize();
        investmentRepository.add(userInvestmentToExpire);
        Double valueToBelieve = investmentToExpired.calculateValueToBelieve(userInvestmentToExpire.getCreation());
        userCurrencyDomainService.execute(buildUserCurrencyOperation(valueToBelieve), userRepository.findById(ownerId));

        return valueToBelieve;
    }

    private IncrementUserCurrency buildUserCurrencyOperation(Double valueToBelieve) {
        return new IncrementUserCurrency(userRepository, valueToBelieve);
    }
}
