package ar.edu.untref.gio.presentation.controller;


import ar.edu.untref.gio.domain.TermDeposit;
import ar.edu.untref.gio.domain.TermDepositInformation;
import ar.edu.untref.gio.domain.interactor.CreateTermDepositInteractor;
import ar.edu.untref.gio.domain.interactor.FindTermDepositInteractor;
import ar.edu.untref.gio.domain.request.CreateTermDepositRequest;
import ar.edu.untref.gio.presentation.response.*;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "Creacion de plazos fijos")
    @RequestMapping(value =  "/users/{ownerId}/deposits", method = RequestMethod.POST,  consumes = {MediaType.APPLICATION_JSON_VALUE})
    public TermDepositResponse createTermDeposit(@RequestBody CreateTermDepositRequest createTermDepositRequest,
                                                 @PathVariable Integer ownerId) {
        TermDeposit termDeposit = createTermDepositInteractor.create(createTermDepositRequest, ownerId);
        return new TermDepositResponseFactory().build(termDeposit);
    }

    @ResponseBody
    @ApiOperation(value = "Busqueda de plazos fijos por id de usuario creador")
    @RequestMapping(value =  "/users/{ownerId}/deposits", method = RequestMethod.GET)
    public TermDepositResponses findTermDeposits(@PathVariable Integer ownerId) {
        List<TermDeposit> termDeposits = this.findTermDepositInteractor.findByOwnerId(ownerId);
        return new TermDepositResponseFactory().build(termDeposits);
    }

    @ResponseBody
    @ApiOperation(value = "Busqueda de la configuracion para la creacion de plazos fijos")
    @RequestMapping(value =  "/deposits", method = RequestMethod.GET)
    public TermDepositInformationResponse findTermDepositInformationForCreation() {
        TermDepositInformation termDepositInformation = this.findTermDepositInteractor.findTermDepositInformationForCreation();
        return new TermDepositInformationResponseFactory().build(termDepositInformation);
    }

}
