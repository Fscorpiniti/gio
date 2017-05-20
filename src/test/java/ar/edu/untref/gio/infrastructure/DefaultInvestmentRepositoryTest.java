package ar.edu.untref.gio.infrastructure;

import ar.edu.untref.gio.domain.Investment;
import ar.edu.untref.gio.domain.UserInvestment;
import ar.edu.untref.gio.domain.UserInvestmentStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context-test.xml"})
@WebAppConfiguration
public class DefaultInvestmentRepositoryTest {

    private static final Integer OWNER_ID = 1;
    private static final Integer INVESTMENT_ID = 1;
    private static final String INVESTMENT_NAME = "Fondo de inversion a plazos fijos";

    @Resource(name = "defaultInvestmentRepository")
    private DefaultInvestmentRepository defaultInvestmentRepository;

    @Test
    public void whenFindInvestmentsFromRepositoryThenResultIsCorrect() {
        List<Investment> investments = defaultInvestmentRepository.getAll();
        Assert.assertFalse(investments.isEmpty());
    }

    @Test
    public void whenFindInvestmentsFromRepositoryThenSizeIsCorrect() {
        List<Investment> investments = defaultInvestmentRepository.getAll();
        int expectedInvestments = 2;
        Assert.assertEquals(expectedInvestments, investments.size());
    }

    @Test
    public void whenFindInvestmentsFromRepositoryThenInvestmentContainsAllCorrectValues() {
        List<Investment> investments = defaultInvestmentRepository.getAll();
        Investment investment = investments.stream().findFirst().get();

        Double expectedAmount = new Double(100);
        Double expectedInterestHigher = new Double(6);
        Double expectedInterestLower = new Double(2);
        Boolean expectedPurchasable = Boolean.TRUE;
        Assert.assertEquals(INVESTMENT_ID, investment.getId());
        Assert.assertEquals(expectedAmount, investment.getAmount());
        Assert.assertEquals(expectedInterestHigher, investment.getInterestHigher());
        Assert.assertEquals(expectedInterestLower, investment.getInterestLower());
        Assert.assertEquals(expectedPurchasable, investment.getPurchasable());
        Assert.assertEquals(INVESTMENT_NAME, investment.getName());
    }

    @Test
    public void whenFindInvestmentByOwnerWithoutInvestmentsThenResultIsEmpty() {
        List<UserInvestment> investments = defaultInvestmentRepository.findByUserId(OWNER_ID);
        Assert.assertTrue(investments.isEmpty());
    }

    @Test
    public void whenFindInvestmentByOwnerWithInvestmentActiveThenResultIsNotEmpty() {
        defaultInvestmentRepository.add(new UserInvestment(OWNER_ID, INVESTMENT_ID, UserInvestmentStatus.ACTIVE));
        List<UserInvestment> investments = defaultInvestmentRepository.findByUserId(OWNER_ID);
        Assert.assertFalse(investments.isEmpty());
    }

    @Test
    public void whenFindInvestmentByOwnerWithInvestmentFinalizedThenResultIsEmpty() {
        defaultInvestmentRepository.add(new UserInvestment(OWNER_ID, INVESTMENT_ID, UserInvestmentStatus.FINALIZED));
        List<UserInvestment> investments = defaultInvestmentRepository.findByUserId(OWNER_ID);
        Assert.assertTrue(investments.isEmpty());
    }

}
