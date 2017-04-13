package ar.edu.untref.gio.presentation.controller;

import ar.edu.untref.gio.domain.User;
import ar.edu.untref.gio.domain.interactor.CreateUserInteractor;
import ar.edu.untref.gio.domain.request.CreateUserRequest;
import ar.edu.untref.gio.presentation.response.UserResponse;
import ar.edu.untref.gio.presentation.response.UserResponseFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Controller
@Transactional
public class UserController {

    @Resource(name = "createUserInteractor")
    private CreateUserInteractor createUserInteractor;

    @ResponseBody
    @RequestMapping(value =  "/users", method = RequestMethod.POST,  consumes = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponse createUser(@RequestBody CreateUserRequest createUserRequest) {
        User user = createUserInteractor.create(createUserRequest);
        return new UserResponseFactory().build(user);
    }

}
