package ar.edu.untref.gio.interactor;


import ar.edu.untref.gio.domain.TermDeposit;
import ar.edu.untref.gio.domain.TermDepositRepository;
import ar.edu.untref.gio.dto.CreateTermDepositDTO;
import ar.edu.untref.gio.validator.DefaultTermDepositValidator;

public class DefaultCreateTermDepositInteractor implements CreateTermDepositInteractor {

    private TermDepositRepository termDepositRepository;

    public DefaultCreateTermDepositInteractor(TermDepositRepository termDepositRepository) {
        this.termDepositRepository = termDepositRepository;
    }

    @Override
    public TermDeposit create(CreateTermDepositDTO createTermDepositDTO) {
        TermDeposit termDeposit = new TermDeposit(createTermDepositDTO.getAmount(), createTermDepositDTO.getRate(),
                createTermDepositDTO.getExpiration(), new DefaultTermDepositValidator());

        termDepositRepository.add(termDeposit);

        return termDeposit;
    }
}
