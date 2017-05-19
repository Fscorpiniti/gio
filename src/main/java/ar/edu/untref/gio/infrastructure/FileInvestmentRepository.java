package ar.edu.untref.gio.infrastructure;

import ar.edu.untref.gio.domain.Investment;
import ar.edu.untref.gio.domain.InvestmentRepository;
import ar.edu.untref.gio.infrastructure.exception.ObjectNotFoundException;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Repository("fileInvestmentRepository")
public class FileInvestmentRepository implements InvestmentRepository {

    private static final String CASUAL_INVESTMENTS_JSON = "casual-investments.json";

    @Override
    public List<Investment> getAll() {
        Type listType = new TypeToken<ArrayList<Investment>>(){}.getType();
        return new Gson().fromJson(getFileReader(), listType);
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
