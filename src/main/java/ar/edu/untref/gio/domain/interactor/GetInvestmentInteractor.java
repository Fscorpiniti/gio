package ar.edu.untref.gio.domain.interactor;

import ar.edu.untref.gio.domain.Investment;

import java.util.List;

public interface GetInvestmentInteractor {

    List<Investment> getAll(Integer ownerId);

    List<Investment> getByOwnerId(Integer ownerId);
}
