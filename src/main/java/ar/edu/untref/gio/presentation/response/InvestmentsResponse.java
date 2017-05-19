package ar.edu.untref.gio.presentation.response;

import ar.edu.untref.gio.domain.Investment;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InvestmentsResponse {

    @JsonProperty("investments")
    private List<InvestmentResponse> investments = new ArrayList<>();

    public InvestmentsResponse(List<Investment> investments) {
        this.investments = investments.stream()
                .map(investment -> buildInvestmentResponse(investment))
                .collect(Collectors.toList());
    }

    private InvestmentResponse buildInvestmentResponse(Investment investment) {
        return new InvestmentResponse(investment.getId(), investment.getAmount(),
                investment.getInterestHigher(), investment.getInterestLower(), investment.getPurchasable(),
                investment.getText());
    }

    public List<InvestmentResponse> getInvestments() {
        return investments;
    }
}
