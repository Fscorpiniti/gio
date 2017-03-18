package ar.edu.untref.gio.interactor;

import ar.edu.untref.gio.domain.TermDeposit;
import ar.edu.untref.gio.dto.CreateTermDepositRequest;

public interface CreateTermDepositInteractor {

    TermDeposit create(CreateTermDepositRequest createTermDepositRequest, Long ownerId);

}