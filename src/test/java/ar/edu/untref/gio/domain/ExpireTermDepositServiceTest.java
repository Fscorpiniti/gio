package ar.edu.untref.gio.domain;

import ar.edu.untref.gio.domain.service.DefaultExpireTermDepositService;
import ar.edu.untref.gio.domain.service.ExpireTermDepositService;
import ar.edu.untref.gio.domain.service.UserCurrencyDomainService;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ExpireTermDepositServiceTest {

    @Mock
    private TermDepositRepository termDepositRepository;

    @Mock
    private UserCurrencyDomainService userCurrencyDomainService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenExpireTermDepositOnDateThenTermDepositIsExpired() {
        TermDeposit termDeposit = Mockito.mock(TermDeposit.class);
        Date expiration = DateTime.now().toDate();
        Mockito.when(termDeposit.getExpiration()).thenReturn(expiration);
        Mockito.when(termDepositRepository.findTermDepositToExpire()).thenReturn(Arrays.asList(termDeposit));

        ExpireTermDepositService expireTermDepositService = buildExpireTermDepositService();
        List<TermDeposit> expired = expireTermDepositService.expire();

        Assert.assertEquals(termDeposit, expired.stream().findFirst().get());
    }

    @Test
    public void whenExpireTermDepositOnDateThenTermDepositStatusIsFinalized() {
        TermDeposit termDeposit = Mockito.spy(new TermDeposit());
        Date expiration = DateTime.now().toDate();
        Mockito.when(termDeposit.getExpiration()).thenReturn(expiration);
        Mockito.when(termDepositRepository.findTermDepositToExpire()).thenReturn(Arrays.asList(termDeposit));

        ExpireTermDepositService expireTermDepositService = buildExpireTermDepositService();
        List<TermDeposit> expired = expireTermDepositService.expire();

        Assert.assertEquals(TermDepositStatus.FINALIZED, expired.stream().findFirst().get().getStatus());
    }

    private ExpireTermDepositService buildExpireTermDepositService() {
        return new DefaultExpireTermDepositService(termDepositRepository, userCurrencyDomainService, userRepository);
    }

}
