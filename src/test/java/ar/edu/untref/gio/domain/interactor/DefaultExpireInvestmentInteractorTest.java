package ar.edu.untref.gio.domain.interactor;

import ar.edu.untref.gio.domain.*;
import ar.edu.untref.gio.domain.service.UserCurrencyDomainService;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class DefaultExpireInvestmentInteractorTest {

    private static final Integer OWNER_ID = 1;
    private static final String TEST = "test";
    private static final Integer VALID_INVESTMENT_ID = 1;
    private static final Double AMOUNT = new Double(100);
    public static final int MINUS_ONE_DAY = 1;

    @Test
    public void whenExpireInvestmentThenValueIsGreatherThanAmount() {
        InvestmentRepository investmentRepository = Mockito.mock(InvestmentRepository.class);
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserCurrencyDomainService userCurrencyDomainService = Mockito.mock(UserCurrencyDomainService.class);
        Mockito.when(investmentRepository.getAll()).thenReturn(buildListInvestment());
        Mockito.when(investmentRepository.findByUserId(OWNER_ID)).thenReturn(Arrays.asList(new UserInvestment(OWNER_ID,
                VALID_INVESTMENT_ID, UserInvestmentStatus.ACTIVE, DateTime.now().minusDays(MINUS_ONE_DAY).toDate())));

        DefaultExpireInvestmentInteractor defaultExpireInvestmentInteractor = new DefaultExpireInvestmentInteractor(
                investmentRepository, userCurrencyDomainService, userRepository);

        Double result = defaultExpireInvestmentInteractor.expire(OWNER_ID, VALID_INVESTMENT_ID);
        Assert.assertTrue(result > AMOUNT);
    }

    private List<Investment> buildListInvestment() {
        Integer investmentId = 1;
        Double interestLower = new Double(2);
        Double interestHigher = new Double(6);
        boolean isPurchasable = false;
        Investment investment = new Investment(investmentId, TEST, interestLower, interestHigher, AMOUNT,
                isPurchasable, TEST);

        Integer otherInvestmentId = 2;
        Investment otherInvestment = new Investment(otherInvestmentId, TEST, interestLower, interestHigher, AMOUNT,
                isPurchasable, TEST);

        return Arrays.asList(investment, otherInvestment);
    }

}
