package ar.edu.untref.gio.domain.interactor;

import ar.edu.untref.gio.domain.TermDeposit;
import ar.edu.untref.gio.domain.request.CreateTermDepositRequest;

public interface CreateTermDepositInteractor {

    TermDeposit create(CreateTermDepositRequest createTermDepositRequest, Integer ownerId);

}