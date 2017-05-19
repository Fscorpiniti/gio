package ar.edu.untref.gio.domain.service;

import ar.edu.untref.gio.domain.TermDeposit;

import java.util.Date;
import java.util.List;

public interface ExpireTermDepositService {

    List<TermDeposit> expire(Date expiration);

    TermDeposit expire(Integer ownerId, Integer termDepositId);
}
