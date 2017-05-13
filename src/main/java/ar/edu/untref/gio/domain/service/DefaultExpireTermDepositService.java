package ar.edu.untref.gio.domain.service;

import ar.edu.untref.gio.domain.TermDeposit;
import ar.edu.untref.gio.domain.TermDepositRepository;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultExpireTermDepositService implements ExpireTermDepositService {

    private TermDepositRepository termDepositRepository;

    public DefaultExpireTermDepositService(TermDepositRepository termDepositRepository) {
        this.termDepositRepository = termDepositRepository;
    }

    @Override
    public List<TermDeposit> expire() {
        return this.termDepositRepository.findTermDepositToExpire()
                .stream()
                .peek(termDeposit -> {
                    termDeposit.finalize();
                    termDepositRepository.add(termDeposit);
                })
                .collect(Collectors.toList());
    }
}
