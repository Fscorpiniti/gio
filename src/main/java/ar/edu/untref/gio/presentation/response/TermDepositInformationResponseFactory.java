package ar.edu.untref.gio.presentation.response;

import ar.edu.untref.gio.domain.TermDepositInformation;

public class TermDepositInformationResponseFactory {

    public TermDepositInformationResponse build(TermDepositInformation termDepositInformation) {
        return new TermDepositInformationResponse(termDepositInformation.getMonthlyRate(), termDepositInformation.getBiMonthlyRate(),
                termDepositInformation.getQuarterlyRate(), termDepositInformation.getSemiAnnualRate(), termDepositInformation.getAnnualRate());
    }
}
