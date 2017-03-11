package ar.edu.untref.gio.interactor;

import ar.edu.untref.gio.domain.TermDeposit;
import ar.edu.untref.gio.dto.CreateTermDepositDTO;

public interface CreateTermDepositInteractor {

    TermDeposit create(CreateTermDepositDTO createTermDepositDTO, Long creatorId);

}