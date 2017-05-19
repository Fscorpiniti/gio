package ar.edu.untref.gio.domain.interactor;

import ar.edu.untref.gio.domain.TermDeposit;

public interface ForceTermDepositExpirationInteractor {

    TermDeposit force(Integer ownerId, Integer termDepositId);

}
