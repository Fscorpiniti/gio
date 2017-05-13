package ar.edu.untref.gio.domain;

import ar.edu.untref.gio.domain.service.ExpireTermDepositService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("expireTermDepositProcess")
public class ExpireTermDepositProcess {

    @Resource(name = "expireTermDepositService")
    private ExpireTermDepositService expireTermDepositService;

    public void process() {
        expireTermDepositService.expire(DateTime.now().toDate());
    }

}
