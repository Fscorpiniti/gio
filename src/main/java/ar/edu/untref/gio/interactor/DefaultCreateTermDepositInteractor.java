package ar.edu.untref.gio.interactor;


import ar.edu.untref.gio.domain.TermDeposit;
import ar.edu.untref.gio.domain.TermDepositRepository;
import ar.edu.untref.gio.dto.CreateTermDepositRequest;
import ar.edu.untref.gio.validator.DefaultTermDepositValidator;

public class DefaultCreateTermDepositInteractor implements CreateTermDepositInteractor {

    private TermDepositRepository termDepositRepository;

    public DefaultCreateTermDepositInteractor(TermDepositRepository termDepositRepository) {
        this.termDepositRepository = termDepositRepository;
    }

    @Override
    public TermDeposit create(CreateTermDepositRequest createTermDepositRequest, Long creatorId) {
        TermDeposit termDeposit = new TermDeposit(createTermDepositRequest.getAmount(), createTermDepositRequest.getRate(),
                createTermDepositRequest.getExpiration(), new DefaultTermDepositValidator(), creatorId);

        termDepositRepository.add(termDeposit);

        return termDeposit;
    }
}
