package ar.edu.untref.gio.domain.service;

import ar.edu.untref.gio.domain.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DefaultExpireTermDepositService implements ExpireTermDepositService {

    private TermDepositRepository termDepositRepository;
    private UserCurrencyDomainService userCurrencyDomainService;
    private UserRepository userRepository;

    public DefaultExpireTermDepositService(TermDepositRepository termDepositRepository,
                                           UserCurrencyDomainService userCurrencyDomainService,
                                           UserRepository userRepository) {
        this.termDepositRepository = termDepositRepository;
        this.userCurrencyDomainService = userCurrencyDomainService;
        this.userRepository = userRepository;
    }

    @Override
    public List<TermDeposit> expire() {
        return this.termDepositRepository.findTermDepositToExpire()
                .stream()
                .peek(termDeposit -> {
                    termDeposit.finalize();
                    termDepositRepository.add(termDeposit);
                    userCurrencyDomainService.execute(buildUserCurrencyOperation(termDeposit), findOwner(termDeposit));
                })
                .collect(Collectors.toList());
    }

    private Optional<User> findOwner(TermDeposit termDeposit) {
        return userRepository.findById(termDeposit.getOwnerId());
    }

    private UserCurrencyOperation buildUserCurrencyOperation(TermDeposit termDeposit) {
        return new IncrementUserCurrency(userRepository, termDeposit.getAmount());
    }
}
