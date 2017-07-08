package ar.edu.untref.gio.presentation.controller;

import ar.edu.untref.gio.domain.Investment;
import ar.edu.untref.gio.domain.interactor.CreateInvestmentInteractor;
import ar.edu.untref.gio.domain.interactor.ExpireInvestmentInteractor;
import ar.edu.untref.gio.domain.interactor.GetInvestmentInteractor;
import ar.edu.untref.gio.domain.service.ExistTokenService;
import ar.edu.untref.gio.presentation.response.ApiError;
import ar.edu.untref.gio.presentation.response.InvestmentsResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@Controller
@Transactional
public class InvestmentController {

    private static final String AUTH_TOKEN = "auth_token";
    private static final String OWNER_ID = "owner_id";
    private static final String INVESTMENT_ID = "investment_id";

    @Resource(name = "getInvestmentInteractor")
    private GetInvestmentInteractor getInvestmentInteractor;

    @Resource(name = "existTokenService")
    private ExistTokenService existTokenService;

    @Resource(name = "createInvestmentInteractor")
    private CreateInvestmentInteractor createInvestmentInteractor;

    @Resource(name = "expireInvestmentInteractor")
    private ExpireInvestmentInteractor expireInvestmentInteractor;

    @ResponseBody
    @ApiOperation(value = "Retorna inversiones casuales para jugar")
    @RequestMapping(value =  "/users/{owner_id}/games/investments", method = RequestMethod.GET)
    public InvestmentsResponse getAll(@PathVariable(OWNER_ID) Integer ownerId) {
        List<Investment> investments = getInvestmentInteractor.getAll(ownerId);
        return new InvestmentsResponse(investments);
    }

    @ResponseBody
    @ApiOperation(value = "Adquisicion del usuario de una inversion casual.")
    @RequestMapping(value =  "/users/{owner_id}/investments/{investment_id}/purchases", method = RequestMethod.POST)
    public InvestmentsResponse create(@PathVariable(OWNER_ID) Integer ownerId,
                                      @PathVariable(INVESTMENT_ID) Integer investmentId,
                                      @RequestHeader(AUTH_TOKEN) String authToken) {
        this.existTokenService.exist(ownerId, authToken);
        List<Investment> investments = createInvestmentInteractor.execute(ownerId, investmentId);
        return new InvestmentsResponse(investments);
    }

    @ResponseBody
    @ApiOperation(value = "Busqueda de inversiones casuales adquiridas por usuario")
    @RequestMapping(value =  "/users/{owner_id}/investments", method = RequestMethod.GET)
    public InvestmentsResponse findByOwner(@PathVariable(OWNER_ID) Integer ownerId,
                                          @RequestHeader(AUTH_TOKEN) String authToken) {
        this.existTokenService.exist(ownerId, authToken);
        List<Investment> investments = getInvestmentInteractor.findByOwnerId(ownerId);
        return new InvestmentsResponse(investments);
    }

    @ResponseBody
    @ApiOperation(value = "Forzar la acreditacion de inversion casual.")
    @RequestMapping(value =  "/users/{owner_id}/investments/{investment_id}/purchases", method = RequestMethod.DELETE)
    public Double finish(@PathVariable(OWNER_ID) Integer ownerId,
                                          @PathVariable(INVESTMENT_ID) Integer investmentId,
                                          @RequestHeader(AUTH_TOKEN) String authToken) {
        this.existTokenService.exist(ownerId, authToken);
        return this.expireInvestmentInteractor.expire(ownerId, investmentId);
    }

    @ResponseBody
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ RuntimeException.class, Exception.class })
    public ApiError handleError(HttpServletRequest request, Throwable exception) {
        return new ApiError(exception.getMessage());
    }

}
