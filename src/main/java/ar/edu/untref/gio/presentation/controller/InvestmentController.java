package ar.edu.untref.gio.presentation.controller;

import ar.edu.untref.gio.domain.Investment;
import ar.edu.untref.gio.domain.interactor.CreateInvestmentInteractor;
import ar.edu.untref.gio.domain.interactor.ExpireInvestmentInteractor;
import ar.edu.untref.gio.domain.interactor.GetInvestmentInteractor;
import ar.edu.untref.gio.domain.service.ExistTokenService;
import ar.edu.untref.gio.presentation.response.InvestmentsResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Controller
@Transactional
public class InvestmentController {

    private static final String AUTH_TOKEN = "auth_token";

    @Resource(name = "getInvestmentInteractor")
    private GetInvestmentInteractor getInvestmentInteractor;

    @Resource(name = "existTokenService")
    private ExistTokenService existTokenService;

    @Resource(name = "createInvestmentInteractor")
    private CreateInvestmentInteractor createInvestmentInteractor;

    @Resource(name = "expireInvestmentInteractor")
    private ExpireInvestmentInteractor expireInvestmentInteractor;

    @ResponseBody
    @ApiOperation(value = "Busqueda de inversiones casuales para jugar")
    @RequestMapping(value =  "/users/{owner_id}/game/investments", method = RequestMethod.GET)
    public InvestmentsResponse getAll(@PathVariable("owner_id") Integer ownerId) {
        List<Investment> investments = getInvestmentInteractor.getAll(ownerId);
        return new InvestmentsResponse(investments);
    }

    @ResponseBody
    @ApiOperation(value = "Creacion de una inversion casual")
    @RequestMapping(value =  "/users/{owner_id}/investments/{investment_id}/purchase", method = RequestMethod.POST)
    public InvestmentsResponse create(@PathVariable("owner_id") Integer ownerId,
                                      @PathVariable("investment_id") Integer investmentId,
                                      @RequestHeader(AUTH_TOKEN) String authToken) {
        this.existTokenService.exist(ownerId, authToken);
        List<Investment> investments = createInvestmentInteractor.execute(ownerId, investmentId);
        return new InvestmentsResponse(investments);
    }

    @ResponseBody
    @ApiOperation(value = "Busqueda de inversiones casuales adquiridas por usuario")
    @RequestMapping(value =  "/users/{owner_id}/investments", method = RequestMethod.GET)
    public InvestmentsResponse findByUser(@PathVariable("owner_id") Integer ownerId,
                                          @RequestHeader(AUTH_TOKEN) String authToken) {
        this.existTokenService.exist(ownerId, authToken);
        List<Investment> investments = getInvestmentInteractor.getByOwnerId(ownerId);
        return new InvestmentsResponse(investments);
    }

    @ResponseBody
    @ApiOperation(value = "Acreditacion de inversion casual")
    @RequestMapping(value =  "/users/{owner_id}/investments/{investment_id}/purchase", method = RequestMethod.DELETE)
    public void finishInvestment(@PathVariable("owner_id") Integer ownerId,
                                          @PathVariable("investment_id") Integer investmentId,
                                          @RequestHeader(AUTH_TOKEN) String authToken) {
        this.existTokenService.exist(ownerId, authToken);
        this.expireInvestmentInteractor.expire(ownerId, investmentId);
    }

}
