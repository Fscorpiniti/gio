package ar.edu.untref.gio.domain.interactor;

import ar.edu.untref.gio.domain.Investment;
import ar.edu.untref.gio.domain.InvestmentRepository;
import ar.edu.untref.gio.domain.UserInvestment;
import ar.edu.untref.gio.domain.UserInvestmentStatus;
import ar.edu.untref.gio.infrastructure.exception.ObjectNotFoundException;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class DefaultCreateInvestmentInteractorTest {

    private static final Integer OWNER_ID = 1;
    private static final Integer INVALID_INVESTMENT_ID = 3;
    private static final String TEST = "test";
    private static final Integer VALID_INVESTMENT_ID = 1;

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void whenCreateInvestmentWithInvalidIdThenExceptionIsThrown() {
        InvestmentRepository investmentRepository = Mockito.mock(InvestmentRepository.class);
        Mockito.when(investmentRepository.getAll()).thenReturn(buildListInvestment());

        DefaultCreateInvestmentInteractor defaultCreateInvestmentInteractor = new
                DefaultCreateInvestmentInteractor(investmentRepository);

        thrown.expect(ObjectNotFoundException.class);
        defaultCreateInvestmentInteractor.execute(OWNER_ID, INVALID_INVESTMENT_ID);
    }

    @Test
    public void whenCreateInvestmentThenInvestmentIsReturned() {
        InvestmentRepository investmentRepository = Mockito.mock(InvestmentRepository.class);
        Mockito.when(investmentRepository.getAll()).thenReturn(buildListInvestment());
        Mockito.when(investmentRepository.findByUserId(OWNER_ID)).thenReturn(Arrays.asList(new UserInvestment(OWNER_ID,
                VALID_INVESTMENT_ID, UserInvestmentStatus.ACTIVE, DateTime.now().toDate())));

        DefaultCreateInvestmentInteractor defaultCreateInvestmentInteractor = new
                DefaultCreateInvestmentInteractor(investmentRepository);

        List<Investment> investments = defaultCreateInvestmentInteractor.execute(OWNER_ID, VALID_INVESTMENT_ID);
        Assert.assertFalse(investments.isEmpty());
    }

    @Test
    public void whenCreateInvestmentThenReturnOnlyInvestmentPurchased() {
        InvestmentRepository investmentRepository = Mockito.mock(InvestmentRepository.class);
        Mockito.when(investmentRepository.getAll()).thenReturn(buildListInvestment());
        Mockito.when(investmentRepository.findByUserId(OWNER_ID)).thenReturn(Arrays.asList(new UserInvestment(OWNER_ID,
                VALID_INVESTMENT_ID, UserInvestmentStatus.ACTIVE, DateTime.now().toDate())));

        DefaultCreateInvestmentInteractor defaultCreateInvestmentInteractor = new
                DefaultCreateInvestmentInteractor(investmentRepository);

        List<Investment> investments = defaultCreateInvestmentInteractor.execute(OWNER_ID, VALID_INVESTMENT_ID);
        int expectedSize = 1;
        Assert.assertEquals(expectedSize, investments.size());
        Assert.assertEquals(VALID_INVESTMENT_ID, investments.stream().findFirst().get().getId());
    }

    private List<Investment> buildListInvestment() {
        Integer investmentId = 1;
        Double interestLower = new Double(2);
        Double interestHigher = new Double(6);
        Double amount = new Double(100);
        boolean isPurchasable = false;
        Investment investment = new Investment(investmentId, TEST, interestLower, interestHigher, amount,
                isPurchasable, TEST);

        Integer otherInvestmentId = 2;
        Investment otherInvestment = new Investment(otherInvestmentId, TEST, interestLower, interestHigher, amount,
                isPurchasable, TEST);

        return Arrays.asList(investment, otherInvestment);
    }

}
