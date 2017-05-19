package ar.edu.untref.gio.domain.service;

import ar.edu.untref.gio.domain.*;
import org.joda.time.DateTime;

import java.util.Date;
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
    public List<TermDeposit> expire(Date expiration) {
        return this.termDepositRepository.findTermDepositToExpire(expiration)
                .stream()
                .peek(termDeposit -> expireTermDeposit(termDeposit))
                .collect(Collectors.toList());
    }

    @Override
    public TermDeposit expire(Integer ownerId, Integer termDepositId) {
        TermDeposit termDeposit = termDepositRepository.findBy(ownerId, termDepositId);
        expireTermDeposit(termDeposit);
        return termDeposit;
    }

    private void expireTermDeposit(TermDeposit termDeposit) {
        termDeposit.finalize();
        termDepositRepository.add(termDeposit);
        userCurrencyDomainService.execute(buildUserCurrencyOperation(termDeposit), findOwner(termDeposit));
    }

    private Optional<User> findOwner(TermDeposit termDeposit) {
        return userRepository.findById(termDeposit.getOwnerId());
    }

    private UserCurrencyOperation buildUserCurrencyOperation(TermDeposit termDeposit) {
        return new IncrementUserCurrency(userRepository, termDeposit.calculateValueToBelieve());
    }
}
