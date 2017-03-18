package ar.edu.untref.gio.persistence;

import ar.edu.untref.gio.domain.TermDeposit;
import ar.edu.untref.gio.domain.TermDepositRepository;
import ar.edu.untref.gio.domain.TermDepositStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository("defaultTermDepositRepository")
public class DefaultTermDepositRepository extends GenericRepository<TermDeposit> implements TermDepositRepository {

    private static final String PARAM_OWNER_ID = "ownerId";
    private static final String PARAM_STATUS = "status";

    @Override
    protected Class<TermDeposit> getEntityClass() {
        return TermDeposit.class;
    }

    @Override
    public List<TermDeposit> findActiveTermDepositsByOwnerId(Integer ownerId) {
        Query query = buildQueryFindByOwnerId(ownerId);
        return query.getResultList();
    }

    private Query buildQueryFindByOwnerId(Integer ownerId) {
        StringBuilder hql = new StringBuilder("from ")
                .append(getEntityClass().getName())
                .append(" this where this.ownerId = :ownerId and this.status = :status");

        return this.getEntityManager()
                .createQuery(hql.toString())
                .setParameter(PARAM_OWNER_ID, ownerId)
                .setParameter(PARAM_STATUS, TermDepositStatus.ACTIVE);
    }
}
