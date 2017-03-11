package ar.edu.untref.gio.validator;

import java.util.Date;

public interface TermDepositValidator {

    void execute(Double amount, Double rate, Date expiration);

}
