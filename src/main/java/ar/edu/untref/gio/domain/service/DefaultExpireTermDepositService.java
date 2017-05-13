package ar.edu.untref.gio.domain.service;

import ar.edu.untref.gio.domain.TermDeposit;
import ar.edu.untref.gio.domain.TermDepositRepository;

import java.util.List;

public class DefaultExpireTermDepositService implements ExpireTermDepositService {

    private TermDepositRepository termDepositRepository;

    public DefaultExpireTermDepositService(TermDepositRepository termDepositRepository) {
        this.termDepositRepository = termDepositRepository;
    }

    @Override
    public List<TermDeposit> expire() {
        return this.termDepositRepository.findTermDepositToExpire();
    }
}
