package ar.edu.untref.gio.infrastructure;

import ar.edu.untref.gio.domain.TermDeposit;
import ar.edu.untref.gio.domain.TermDepositInformation;
import ar.edu.untref.gio.domain.TermDepositRepository;
import ar.edu.untref.gio.domain.TermDepositStatus;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.Query;
import java.util.List;
import java.util.Properties;

@Repository("defaultTermDepositRepository")
public class DefaultTermDepositRepository extends GenericRepository<TermDeposit> implements TermDepositRepository {

    private static final String PARAM_OWNER_ID = "ownerId";
    private static final String PARAM_STATUS = "status";
    private static final String MONTHLY_RATE = "monthly.rate";
    private static final String BI_MONTHLY_RATE = "biMonthly.rate";
    private static final String QUARTERLY_RATE = "quarterly.rate";
    private static final String SEMI_ANNUAL_RATE = "semiAnnual.rate";
    private static final String ANNUAL_RATE = "annual.rate";

    @Resource(name = "props")
    private Properties properties;

    @Override
    protected Class<TermDeposit> getEntityClass() {
        return TermDeposit.class;
    }

    @Override
    public List<TermDeposit> findActiveTermDepositsByOwnerId(Integer ownerId) {
        Query query = buildQueryFindByOwnerId(ownerId);
        return query.getResultList();
    }

    @Override
    public TermDepositInformation findTermDepositInformationForCreation() {
        Double monthlyRate = Double.valueOf(properties.getProperty(MONTHLY_RATE));
        Double biMonthlyRate = Double.valueOf(properties.getProperty(BI_MONTHLY_RATE));
        Double quarterlyRate = Double.valueOf(properties.getProperty(QUARTERLY_RATE));
        Double semiAnnualRate = Double.valueOf(properties.getProperty(SEMI_ANNUAL_RATE));
        Double annualRate = Double.valueOf(properties.getProperty(ANNUAL_RATE));

        return new TermDepositInformation(monthlyRate, biMonthlyRate, quarterlyRate, semiAnnualRate, annualRate);
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
