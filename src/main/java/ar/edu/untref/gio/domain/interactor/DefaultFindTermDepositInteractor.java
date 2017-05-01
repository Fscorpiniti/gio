package ar.edu.untref.gio.domain.interactor;

import ar.edu.untref.gio.domain.TermDeposit;
import ar.edu.untref.gio.domain.TermDepositInformation;
import ar.edu.untref.gio.domain.TermDepositRepository;
import com.google.common.base.Preconditions;

import java.util.List;

public class DefaultFindTermDepositInteractor implements FindTermDepositInteractor {

    private TermDepositRepository termDepositRepository;

    public DefaultFindTermDepositInteractor(TermDepositRepository termDepositRepository) {
        this.termDepositRepository = termDepositRepository;
    }

    public List<TermDeposit> findByOwnerId(Integer ownerId) {
        Preconditions.checkNotNull(ownerId);
        return termDepositRepository.findActiveTermDepositsByOwnerId(ownerId);
    }

    @Override
    public TermDepositInformation findTermDepositInformationForCreation() {
        return termDepositRepository.findTermDepositInformationForCreation();
    }

}
