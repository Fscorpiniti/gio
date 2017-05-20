package ar.edu.untref.gio.domain.interactor;

import ar.edu.untref.gio.domain.Investment;

import java.util.List;

public interface CreateInvestmentInteractor {

    List<Investment> execute(Integer ownerId, Integer investmentId);

}
