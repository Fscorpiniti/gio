package ar.edu.untref.gio.domain.validator;

import java.util.Date;

public interface TermDepositValidator {

    void execute(Double amount, Double rate, Date expiration,
                 Integer creatorId, Integer duration);

}
