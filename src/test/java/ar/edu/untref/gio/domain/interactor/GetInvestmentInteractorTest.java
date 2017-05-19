package ar.edu.untref.gio.domain.interactor;

import ar.edu.untref.gio.domain.InvestmentRepository;
import org.junit.Test;
import org.mockito.Mockito;

public class GetInvestmentInteractorTest {

    public static final int NUMBER_OF_INVOCATIONS = 1;

    @Test
    public void whenGetAllInvestmentsThenConsumeRepository() {
        InvestmentRepository investmentRepository = Mockito.mock(InvestmentRepository.class);
        GetInvestmentInteractor getInvestmentInteractor = new DefaultGetInvestmentInteractor(investmentRepository);
        getInvestmentInteractor.getAll();
        Mockito.verify(investmentRepository, Mockito.times(NUMBER_OF_INVOCATIONS)).getAll();
    }

}
