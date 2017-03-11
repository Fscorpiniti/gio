package ar.edu.untref.gio.action;


import ar.edu.untref.gio.domain.TermDeposit;
import ar.edu.untref.gio.domain.TermDepositRepository;
import ar.edu.untref.gio.dto.CreateTermDepositDTO;
import ar.edu.untref.gio.validator.DefaultTermDepositValidator;

public class DefaultCreateTermDepositAction implements CreateTermDepositAction {

    private TermDepositRepository termDepositRepository;

    public DefaultCreateTermDepositAction(TermDepositRepository termDepositRepository) {
        this.termDepositRepository = termDepositRepository;
    }

    @Override
    public TermDeposit create(CreateTermDepositDTO createTermDepositDTO) {
        TermDeposit termDeposit = new TermDeposit(createTermDepositDTO.getAmount(), createTermDepositDTO.getRate(),
                createTermDepositDTO.getExpiration(), new DefaultTermDepositValidator());
        return termDeposit;
    }
}
