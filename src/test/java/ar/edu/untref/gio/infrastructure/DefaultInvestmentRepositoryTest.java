package ar.edu.untref.gio.infrastructure;

import ar.edu.untref.gio.domain.Investment;
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

    @Resource(name = "fileInvestmentRepository")
    private FileInvestmentRepository fileInvestmentRepository;

    @Test
    public void whenFindInvestmentsFromRepositoryThenResultIsCorrect() {
        List<Investment> investments = fileInvestmentRepository.getAll();
        Assert.assertFalse(investments.isEmpty());
    }

    @Test
    public void whenFindInvestmentsFromRepositoryThenSizeIsCorrect() {
        List<Investment> investments = fileInvestmentRepository.getAll();
        int expectedInvestments = 2;
        Assert.assertEquals(expectedInvestments, investments.size());
    }

    @Test
    public void whenFindInvestmentsFromRepositoryThenInvestmentContainsAllCorrectValues() {
        List<Investment> investments = fileInvestmentRepository.getAll();
        Investment investment = investments.stream().findFirst().get();

        Integer expectedInvestmentId = 1;
        Double expectedAmount = new Double(100);
        Double expectedInterestHigher = new Double(6);
        Double expectedInterestLower = new Double(2);
        Boolean expectedPurchasable = Boolean.TRUE;
        Assert.assertEquals(expectedInvestmentId, investment.getId());
        Assert.assertEquals(expectedAmount, investment.getAmount());
        Assert.assertEquals(expectedInterestHigher, investment.getInterestHigher());
        Assert.assertEquals(expectedInterestLower, investment.getInterestLower());
        Assert.assertEquals(expectedPurchasable, investment.getPurchasable());
    }

}
