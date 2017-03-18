package ar.edu.untref.gio.domain;

import java.util.List;

public interface TermDepositRepository extends Repository<TermDeposit> {

    List<TermDeposit> findByOwnerId(Integer ownerId);

}
