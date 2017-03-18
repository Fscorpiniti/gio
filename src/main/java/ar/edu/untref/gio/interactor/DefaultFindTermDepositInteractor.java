package ar.edu.untref.gio.interactor;

import com.google.common.base.Preconditions;

public class DefaultFindTermDepositInteractor {

    public void findByOwnerId(Long ownerId) {
        Preconditions.checkNotNull(ownerId);
    }

}
