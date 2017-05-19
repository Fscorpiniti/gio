package ar.edu.untref.gio.domain.interactor;

import ar.edu.untref.gio.domain.TermDeposit;
import ar.edu.untref.gio.domain.service.ExpireTermDepositService;

public class DefaultForceTermDepositInteractor implements ForceTermDepositExpirationInteractor {

    private ExpireTermDepositService expireTermDepositService;

    public DefaultForceTermDepositInteractor(ExpireTermDepositService expireTermDepositService) {
        this.expireTermDepositService = expireTermDepositService;
    }

    @Override
    public TermDeposit force(Integer ownerId, Integer termDepositId) {
        return expireTermDepositService.expire(ownerId, termDepositId);
    }

}
