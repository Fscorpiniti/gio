package ar.edu.untref.gio.domain;

import java.util.List;

public interface TermDepositRepository extends Repository<TermDeposit> {

    List<TermDeposit> findActiveTermDepositsByOwnerId(Integer ownerId);

    TermDepositInformation findTermDepositInformationForCreation();

    List<TermDeposit> findTermDepositToExpire();
}
