package ar.edu.untref.gio.domain;

import com.google.common.base.Preconditions;

public class TermDepositInformation {

    private Double monthlyRate;
    private Double biMonthlyRate;
    private Double quarterlyRate;
    private Double semiAnnualRate;
    private Double annualRate;

    public TermDepositInformation(Double monthlyRate, Double biMonthlyRate, Double quarterlyRate, Double semiAnnualRate, Double annualRate) {
        check(monthlyRate, biMonthlyRate, quarterlyRate, semiAnnualRate, annualRate);
        this.monthlyRate = monthlyRate;
        this.biMonthlyRate = biMonthlyRate;
        this.quarterlyRate = quarterlyRate;
        this.semiAnnualRate = semiAnnualRate;
        this.annualRate = annualRate;
    }

    private void check(Double monthlyRate, Double biMonthlyRate, Double quarterlyRate, Double semiAnnualRate, Double annualRate) {
        Preconditions.checkNotNull(monthlyRate);
        Preconditions.checkNotNull(biMonthlyRate);
        Preconditions.checkNotNull(quarterlyRate);
        Preconditions.checkNotNull(semiAnnualRate);
        Preconditions.checkNotNull(annualRate);
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
