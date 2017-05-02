package ar.edu.untref.gio.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TermDepositInformationResponse {

    @JsonProperty("monthly_rate")
    private Double monthlyRate;

    @JsonProperty("bi_monthly_rate")
    private Double biMonthlyRate;

    @JsonProperty("quarterly_rate")
    private Double quarterlyRate;

    @JsonProperty("semi_annual_rate")
    private Double semiAnnualRate;

    @JsonProperty("annual_rate")
    private Double annualRate;

    public TermDepositInformationResponse(Double monthlyRate, Double biMonthlyRate, Double quarterlyRate,
                                          Double semiAnnualRate, Double annualRate) {
        this.monthlyRate = monthlyRate;
        this.biMonthlyRate = biMonthlyRate;
        this.quarterlyRate = quarterlyRate;
        this.semiAnnualRate = semiAnnualRate;
        this.annualRate = annualRate;
    }


    public Double getMonthlyRate() {
        return monthlyRate;
    }

    public Double getBiMonthlyRate() {
        return biMonthlyRate;
    }

    public Double getQuarterlyRate() {
        return quarterlyRate;
    }

    public Double getSemiAnnualRate() {
        return semiAnnualRate;
    }

    public Double getAnnualRate() {
        return annualRate;
    }
}
