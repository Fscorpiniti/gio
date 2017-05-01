package ar.edu.untref.gio.domain.interactor;

import ar.edu.untref.gio.domain.TermDeposit;
import ar.edu.untref.gio.domain.TermDepositInformation;

import java.util.List;

public interface FindTermDepositInteractor {

    List<TermDeposit> findByOwnerId(Integer ownerId);

    TermDepositInformation findTermDepositInformationForCreation();
}
