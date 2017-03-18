package ar.edu.untref.gio.interactor;

import ar.edu.untref.gio.domain.TermDeposit;

import java.util.List;

public interface FindTermDepositInteractor {

    List<TermDeposit> findByOwnerId(Long ownerId);

}