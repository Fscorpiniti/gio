package ar.edu.untref.gio.presentation.controller;


import ar.edu.untref.gio.domain.TermDeposit;
import ar.edu.untref.gio.domain.TermDepositInformation;
import ar.edu.untref.gio.domain.interactor.CreateTermDepositInteractor;
import ar.edu.untref.gio.domain.interactor.FindTermDepositInteractor;
import ar.edu.untref.gio.domain.interactor.ForceTermDepositExpirationInteractor;
import ar.edu.untref.gio.domain.request.CreateTermDepositRequest;
import ar.edu.untref.gio.domain.service.ExistTokenService;
import ar.edu.untref.gio.presentation.response.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@Controller
@Transactional
public class TermDepositController {

    private static final String AUTH_TOKEN = "auth_token";

    @Resource(name = "createTermDepositInteractor")
    private CreateTermDepositInteractor createTermDepositInteractor;

    @Resource(name = "findTermDepositInteractor")
    private FindTermDepositInteractor findTermDepositInteractor;

    @Resource(name = "existTokenService")
    private ExistTokenService existTokenService;

    @Resource(name = "forceTermDepositExpirationInteractor")
    private ForceTermDepositExpirationInteractor forceTermDepositExpirationInteractor;

    @ResponseBody
    @ApiOperation(value = "Creacion de plazos fijos")
    @RequestMapping(value =  "/users/{ownerId}/deposits", method = RequestMethod.POST,  consumes = {MediaType.APPLICATION_JSON_VALUE})
    public TermDepositResponse createTermDeposit(@RequestBody CreateTermDepositRequest createTermDepositRequest,
                                                 @PathVariable Integer ownerId,
                                                 @RequestHeader(value = AUTH_TOKEN) String authToken) {
        this.existTokenService.exist(ownerId, authToken);
        TermDeposit termDeposit = createTermDepositInteractor.create(createTermDepositRequest, ownerId);
        return new TermDepositResponseFactory().build(termDeposit);
    }

    @ResponseBody
    @ApiOperation(value = "Forzar la acreditacion del plazo fijo")
    @RequestMapping(value =  "/users/{ownerId}/deposits/{termDepositId}", method = RequestMethod.DELETE)
    public TermDepositResponse force(@PathVariable Integer ownerId, @PathVariable Integer termDepositId,
                                     @RequestHeader(value = AUTH_TOKEN) String authToken) {
        this.existTokenService.exist(ownerId, authToken);
        TermDeposit termDeposit = this.forceTermDepositExpirationInteractor.force(ownerId, termDepositId);
        return new TermDepositResponseFactory().build(termDeposit);
    }

    @ResponseBody
    @ApiOperation(value = "Busqueda de plazos fijos por id de usuario creador")
    @RequestMapping(value =  "/users/{ownerId}/deposits", method = RequestMethod.GET)
    public TermDepositResponses findTermDeposits(@PathVariable Integer ownerId,
                                                 @RequestHeader(value = AUTH_TOKEN) String authToken) {
        this.existTokenService.exist(ownerId, authToken);
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

    @ResponseBody
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ RuntimeException.class, Exception.class })
    public ApiError handleError(HttpServletRequest request, Throwable exception) {
        return new ApiError(exception.getMessage());
    }

}
