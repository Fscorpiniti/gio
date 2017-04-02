package ar.edu.untref.gio.controller;

import ar.edu.untref.gio.domain.User;
import ar.edu.untref.gio.request.LoginRequest;
import ar.edu.untref.gio.response.UserResponse;
import ar.edu.untref.gio.response.UserResponseFactory;
import ar.edu.untref.gio.security.AuthenticationInteractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Optional;

@Controller
@Transactional
public class LoginController {

    @Resource(name = "authenticationInteractor")
    private AuthenticationInteractor authenticationInteractor;

    @ResponseBody
    @RequestMapping(value =  "/login", method = RequestMethod.POST,  consumes = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponse login(@RequestBody LoginRequest loginRequest) {
        Optional<User> user = this.authenticationInteractor.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        return new UserResponseFactory().build(user.get());
    }

}
