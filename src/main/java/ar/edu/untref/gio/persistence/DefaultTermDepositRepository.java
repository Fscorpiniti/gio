package ar.edu.untref.gio.persistence;

import ar.edu.untref.gio.domain.TermDeposit;
import ar.edu.untref.gio.domain.TermDepositRepository;
import org.springframework.stereotype.Repository;

@Repository("defaultTermDepositRepository")
public class DefaultTermDepositRepository extends GenericRepository<TermDeposit> implements TermDepositRepository {

    @Override
    protected Class<TermDeposit> getEntityClass() {
        return TermDeposit.class;
    }

}
