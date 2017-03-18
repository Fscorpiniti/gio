package ar.edu.untref.gio.response;

import ar.edu.untref.gio.domain.TermDeposit;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TermDepositResponseFactory {

    public TermDepositResponse build(TermDeposit termDeposit) {
        return new TermDepositResponse(termDeposit.getId(), termDeposit.getAmount(), termDeposit.getRate(),
                termDeposit.getExpiration(), termDeposit.getStatus(), termDeposit.getOwnerId());
    }

    public TermDepositResponses build(List<TermDeposit> termDeposits) {
        Set<TermDepositResponse> termDepositResponses = termDeposits.stream()
                .map(termDeposit -> build(termDeposit)).collect(Collectors.toSet());
        return new TermDepositResponses(termDepositResponses);
    }

}
