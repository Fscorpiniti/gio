package ar.edu.untref.gio.controller;


import ar.edu.untref.gio.domain.TermDeposit;
import ar.edu.untref.gio.interactor.CreateTermDepositInteractor;
import ar.edu.untref.gio.interactor.FindTermDepositInteractor;
import ar.edu.untref.gio.request.CreateTermDepositRequest;
import ar.edu.untref.gio.response.TermDepositResponse;
import ar.edu.untref.gio.response.TermDepositResponseFactory;
import ar.edu.untref.gio.response.TermDepositResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class TermDepositController {

    private CreateTermDepositInteractor createTermDepositInteractor;
    private FindTermDepositInteractor findTermDepositInteractor;

    public TermDepositController(CreateTermDepositInteractor createTermDepositInteractor,
                                 FindTermDepositInteractor findTermDepositInteractor) {
        this.createTermDepositInteractor = createTermDepositInteractor;
        this.findTermDepositInteractor = findTermDepositInteractor;
    }

    @ResponseBody
    @RequestMapping(value =  "users/{ownerId}/deposits", method = RequestMethod.POST,  consumes = {MediaType.APPLICATION_JSON_VALUE})
    public TermDepositResponse createTermDeposit(@RequestBody CreateTermDepositRequest createTermDepositRequest,
                                                 @PathVariable Integer ownerId) {
        TermDeposit termDeposit = createTermDepositInteractor.create(createTermDepositRequest, ownerId);
        return new TermDepositResponseFactory().build(termDeposit);
    }

    @ResponseBody
    @RequestMapping(value =  "users/{ownerId}/deposits", method = RequestMethod.GET,  consumes = {MediaType.APPLICATION_JSON_VALUE})
    public TermDepositResponses findTermDeposits(@PathVariable Integer ownerId) {
        List<TermDeposit> termDeposits = this.findTermDepositInteractor.findByOwnerId(ownerId);
        return new TermDepositResponseFactory().build(termDeposits);
    }

}