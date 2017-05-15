package ar.edu.untref.gio.presentation.controller;

import ar.edu.untref.gio.domain.User;
import ar.edu.untref.gio.domain.request.LoginRequest;
import ar.edu.untref.gio.domain.service.CreateTokenService;
import ar.edu.untref.gio.presentation.response.UserResponse;
import ar.edu.untref.gio.presentation.response.UserResponseFactory;
import ar.edu.untref.gio.domain.interactor.AuthenticationInteractor;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Optional;

@Controller
@Transactional
public class LoginController {

    private static final String AUTH_TOKEN = "auth_token";

    @Resource(name = "authenticationInteractor")
    private AuthenticationInteractor authenticationInteractor;

    @Resource(name = "createTokenService")
    private CreateTokenService createTokenService;

    @ResponseBody
    @ApiOperation(value = "Login")
    @RequestMapping(value =  "/login", method = RequestMethod.POST,  consumes = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponse login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        Optional<User> user = this.authenticationInteractor.authenticate(loginRequest.getEmail(),
                loginRequest.getPassword());
        response.setHeader(AUTH_TOKEN, createTokenService.create(user.get().getId()));
        return new UserResponseFactory().build(user.get());
    }

}
