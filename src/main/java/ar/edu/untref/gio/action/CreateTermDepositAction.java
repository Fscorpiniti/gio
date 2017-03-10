package ar.edu.untref.gio.action;

import ar.edu.untref.gio.domain.TermDeposit;
import ar.edu.untref.gio.dto.CreateTermDepositDTO;

public interface CreateTermDepositAction {

    TermDeposit create(CreateTermDepositDTO createTermDepositDTO);

}