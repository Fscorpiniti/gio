package ar.edu.untref.gio.presentation.controller;


import ar.edu.untref.gio.domain.TermDeposit;
import ar.edu.untref.gio.domain.interactor.CreateTermDepositInteractor;
import ar.edu.untref.gio.domain.interactor.FindTermDepositInteractor;
import ar.edu.untref.gio.domain.request.CreateTermDepositRequest;
import ar.edu.untref.gio.presentation.response.TermDepositResponse;
import ar.edu.untref.gio.presentation.response.TermDepositResponseFactory;
import ar.edu.untref.gio.presentation.response.TermDepositResponses;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Controller
@Transactional
public class TermDepositController {

    @Resource(name = "createTermDepositInteractor")
    private CreateTermDepositInteractor createTermDepositInteractor;

    @Resource(name = "findTermDepositInteractor")
    private FindTermDepositInteractor findTermDepositInteractor;

    @ResponseBody
    @RequestMapping(value =  "/users/{ownerId}/deposits", method = RequestMethod.POST,  consumes = {MediaType.APPLICATION_JSON_VALUE})
    public TermDepositResponse createTermDeposit(@RequestBody CreateTermDepositRequest createTermDepositRequest,
                                                 @PathVariable Integer ownerId) {
        TermDeposit termDeposit = createTermDepositInteractor.create(createTermDepositRequest, ownerId);
        return new TermDepositResponseFactory().build(termDeposit);
    }

    @ResponseBody
    @RequestMapping(value =  "/users/{ownerId}/deposits", method = RequestMethod.GET,  consumes = {MediaType.APPLICATION_JSON_VALUE})
    public TermDepositResponses findTermDeposits(@PathVariable Integer ownerId) {
        List<TermDeposit> termDeposits = this.findTermDepositInteractor.findByOwnerId(ownerId);
        return new TermDepositResponseFactory().build(termDeposits);
    }

}
