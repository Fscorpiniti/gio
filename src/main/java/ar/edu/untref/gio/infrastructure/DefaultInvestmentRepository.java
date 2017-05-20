package ar.edu.untref.gio.infrastructure;

import ar.edu.untref.gio.domain.Investment;
import ar.edu.untref.gio.domain.InvestmentRepository;
import ar.edu.untref.gio.domain.UserInvestment;
import ar.edu.untref.gio.domain.UserInvestmentStatus;
import ar.edu.untref.gio.infrastructure.exception.ObjectNotFoundException;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Repository("defaultInvestmentRepository")
public class DefaultInvestmentRepository extends GenericRepository<UserInvestment> implements InvestmentRepository {

    private static final String CASUAL_INVESTMENTS_JSON = "casual-investments.json";
    private static final String PARAM_OWNER_ID = "ownerId";
    private static final String PARAM_STATUS = "status";

    @Override
    protected Class<UserInvestment> getEntityClass() {
        return UserInvestment.class;
    }

    @Override
    public List<Investment> getAll() {
        Type listType = new TypeToken<ArrayList<Investment>>(){}.getType();
        return new Gson().fromJson(getFileReader(), listType);
    }

    @Override
    public List<UserInvestment> findByUserId(Integer ownerId) {
        StringBuilder hql = new StringBuilder("from ");
        hql.append(getEntityClass().getName());
        hql.append(" this where this.ownerId = :ownerId AND this.status = :status");

        Query query = this.getEntityManager().createQuery(hql.toString());
        query.setParameter(PARAM_OWNER_ID, ownerId);
        query.setParameter(PARAM_STATUS, UserInvestmentStatus.ACTIVE);

        return query.getResultList();
    }

    private FileReader getFileReader() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(CASUAL_INVESTMENTS_JSON).getFile());
            return new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new ObjectNotFoundException("Inversiones casuales no encontradas.");
        }
    }
}
