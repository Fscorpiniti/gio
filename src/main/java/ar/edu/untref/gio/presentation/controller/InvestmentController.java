package ar.edu.untref.gio.presentation.controller;

import ar.edu.untref.gio.domain.Investment;
import ar.edu.untref.gio.domain.interactor.GetInvestmentInteractor;
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

    @Resource(name = "getInvestmentInteractor")
    private GetInvestmentInteractor getInvestmentInteractor;

    @ResponseBody
    @ApiOperation(value = "Busqueda de inversiones casuales")
    @RequestMapping(value =  "/investments", method = RequestMethod.GET)
    public InvestmentsResponse getAll() {
        List<Investment> investments = getInvestmentInteractor.getAll();
        return new InvestmentsResponse(investments);
    }

}
