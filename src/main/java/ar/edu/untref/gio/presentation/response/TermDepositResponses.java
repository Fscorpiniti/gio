package ar.edu.untref.gio.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class TermDepositResponses {

    @JsonProperty("result")
    private Set<TermDepositResponse> termDepositResponses;

    public TermDepositResponses(Set<TermDepositResponse> termDepositResponses) {
        this.termDepositResponses = termDepositResponses;
    }

    public Set<TermDepositResponse> getTermDepositResponses() {
        return termDepositResponses;
    }
}
