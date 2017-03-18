package ar.edu.untref.gio.interactor;


import ar.edu.untref.gio.domain.TermDeposit;
import ar.edu.untref.gio.domain.TermDepositRepository;
import ar.edu.untref.gio.dto.CreateTermDepositRequest;
import ar.edu.untref.gio.validator.DefaultTermDepositValidator;
import com.google.common.base.Preconditions;

public class DefaultCreateTermDepositInteractor implements CreateTermDepositInteractor {

    private TermDepositRepository termDepositRepository;
    private static final String TERM_DEPOSIT_REPOSITORY_IS_REQUIRED = "Term deposit Repository is required";

    public DefaultCreateTermDepositInteractor(TermDepositRepository termDepositRepository) {
        Preconditions.checkNotNull(termDepositRepository, TERM_DEPOSIT_REPOSITORY_IS_REQUIRED);
        this.termDepositRepository = termDepositRepository;
    }

    @Override
    public TermDeposit create(CreateTermDepositRequest createTermDepositRequest, Long ownerId) {
        TermDeposit termDeposit = new TermDeposit(createTermDepositRequest.getAmount(), createTermDepositRequest.getRate(),
                createTermDepositRequest.getExpiration(), new DefaultTermDepositValidator(), ownerId);

        termDepositRepository.add(termDeposit);

        return termDeposit;
    }
}
