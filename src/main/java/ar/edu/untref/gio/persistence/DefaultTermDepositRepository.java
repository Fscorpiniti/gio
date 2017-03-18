package ar.edu.untref.gio.persistence;

import ar.edu.untref.gio.domain.TermDeposit;
import ar.edu.untref.gio.domain.TermDepositRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository("defaultTermDepositRepository")
public class DefaultTermDepositRepository extends GenericRepository<TermDeposit> implements TermDepositRepository {

    private static final String PARAM_OWNER_ID = "ownerId";

    @Override
    protected Class<TermDeposit> getEntityClass() {
        return TermDeposit.class;
    }

    @Override
    public List<TermDeposit> findByOwnerId(Long ownerId) {
        Query query = buildQueryFindByOwnerId(ownerId);
        return query.getResultList();
    }

    private Query buildQueryFindByOwnerId(Long ownerId) {
        StringBuilder hql = new StringBuilder("from ")
                .append(getEntityClass().getName())
                .append(" this where this.ownerId = :ownerId");

        return this.getEntityManager()
                .createQuery(hql.toString())
                .setParameter(PARAM_OWNER_ID, ownerId);
    }
}
