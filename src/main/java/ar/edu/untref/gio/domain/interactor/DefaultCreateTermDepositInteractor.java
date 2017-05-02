package ar.edu.untref.gio.domain.interactor;


import ar.edu.untref.gio.domain.*;
import ar.edu.untref.gio.domain.exception.UserNotFoundException;
import ar.edu.untref.gio.domain.request.CreateTermDepositRequest;
import ar.edu.untref.gio.domain.service.UserCurrencyDomainService;
import com.google.common.base.Preconditions;

import java.util.Optional;

public class DefaultCreateTermDepositInteractor implements CreateTermDepositInteractor {

    private static final String FIND_USER_INTERACTOR_IS_REQUIRED = "FindUserInteractor is required";
    private static final String USER_CURRENCIES_IS_REQUIRED = "User currencies is required";
    private static final String TERM_DEPOSIT_REPOSITORY_IS_REQUIRED = "Term deposit Repository is required";
    public static final String USER_REPOSITORY_IS_REQUIRED = "User Repository is required";

    private TermDepositRepository termDepositRepository;
    private FindUserInteractor findUserInteractor;
    private UserCurrencyDomainService userCurrencyDomainService;
    private UserRepository userRepository;

    public DefaultCreateTermDepositInteractor(TermDepositRepository termDepositRepository, FindUserInteractor findUserInteractor,
                                              UserCurrencyDomainService userCurrencyDomainService, UserRepository userRepository) {
        Preconditions.checkNotNull(termDepositRepository, TERM_DEPOSIT_REPOSITORY_IS_REQUIRED);
        Preconditions.checkNotNull(findUserInteractor, FIND_USER_INTERACTOR_IS_REQUIRED);
        Preconditions.checkNotNull(userCurrencyDomainService, USER_CURRENCIES_IS_REQUIRED);
        Preconditions.checkNotNull(userRepository, USER_REPOSITORY_IS_REQUIRED);

        this.termDepositRepository = termDepositRepository;
        this.findUserInteractor = findUserInteractor;
        this.userCurrencyDomainService = userCurrencyDomainService;
        this.userRepository = userRepository;
    }

    @Override
    public TermDeposit create(CreateTermDepositRequest createTermDepositRequest, Integer ownerId) {
        Optional<User> user = findUserInteractor.findById(ownerId);
        userCurrencyDomainService.execute(buildUserCurrencyOperation(createTermDepositRequest), user);

        TermDeposit termDeposit = buildTermDeposit(createTermDepositRequest, ownerId);

        termDepositRepository.add(termDeposit);

        return termDeposit;
    }

    private UserCurrencyOperation buildUserCurrencyOperation(CreateTermDepositRequest createTermDepositRequest) {
        return new DecrementUserCurrency(userRepository, createTermDepositRequest.getAmount());
    }

    private TermDeposit buildTermDeposit(CreateTermDepositRequest createTermDepositRequest, Integer ownerId) {
        return new TermDepositBuilder().withAmount(createTermDepositRequest.getAmount())
                .withExpiration(createTermDepositRequest.getExpiration()).withOwnerId(ownerId)
                .withRate(createTermDepositRequest.getRate()).build();
    }
}
