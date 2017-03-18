package ar.edu.untref.gio.interactor;

import ar.edu.untref.gio.domain.TermDepositRepository;
import com.google.common.base.Preconditions;

public class DefaultFindTermDepositInteractor implements FindTermDepositInteractor {

    public DefaultFindTermDepositInteractor(TermDepositRepository termDepositRepository) {

    }

    public void findByOwnerId(Long ownerId) {
        Preconditions.checkNotNull(ownerId);
    }

}
