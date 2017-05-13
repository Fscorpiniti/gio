package ar.edu.untref.gio.domain;

import ar.edu.untref.gio.domain.service.DefaultExpireTermDepositService;
import ar.edu.untref.gio.domain.service.ExpireTermDepositService;
import ar.edu.untref.gio.domain.service.UserCurrencyDomainService;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ExpireTermDepositServiceTest {

    @Test
    public void whenExpireTermDepositOnDateThenTermDepositIsExpired() {
        TermDepositRepository termDepositRepository = Mockito.mock(TermDepositRepository.class);
        UserCurrencyDomainService userCurrencyDomainService = Mockito.mock(UserCurrencyDomainService.class);
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        TermDeposit termDeposit = Mockito.mock(TermDeposit.class);
        Date expiration = DateTime.now().toDate();
        Mockito.when(termDeposit.getExpiration()).thenReturn(expiration);
        Mockito.when(termDepositRepository.findTermDepositToExpire()).thenReturn(Arrays.asList(termDeposit));

        ExpireTermDepositService expireTermDepositService = new DefaultExpireTermDepositService(termDepositRepository,
                userCurrencyDomainService, userRepository);
        List<TermDeposit> expired = expireTermDepositService.expire();

        Assert.assertEquals(termDeposit, expired.stream().findFirst().get());
    }

    @Test
    public void whenExpireTermDepositOnDateThenTermDepositStatusIsFinalized() {
        TermDepositRepository termDepositRepository = Mockito.mock(TermDepositRepository.class);
        UserCurrencyDomainService userCurrencyDomainService = Mockito.mock(UserCurrencyDomainService.class);
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        TermDeposit termDeposit = Mockito.spy(new TermDeposit());
        Date expiration = DateTime.now().toDate();
        Mockito.when(termDeposit.getExpiration()).thenReturn(expiration);
        Mockito.when(termDepositRepository.findTermDepositToExpire()).thenReturn(Arrays.asList(termDeposit));

        ExpireTermDepositService expireTermDepositService = new DefaultExpireTermDepositService(termDepositRepository,
                userCurrencyDomainService, userRepository);
        List<TermDeposit> expired = expireTermDepositService.expire();

        Assert.assertEquals(TermDepositStatus.FINALIZED, expired.stream().findFirst().get().getStatus());
    }

}
