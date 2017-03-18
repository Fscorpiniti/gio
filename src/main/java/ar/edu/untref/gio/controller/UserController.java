package ar.edu.untref.gio.controller;

import ar.edu.untref.gio.domain.User;
import ar.edu.untref.gio.interactor.CreateUserInteractor;
import ar.edu.untref.gio.request.CreateUserRequest;
import ar.edu.untref.gio.response.UserResponse;
import ar.edu.untref.gio.response.UserResponseFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;

@Transactional
public class UserController {

    private CreateUserInteractor createUserInteractor;

    public UserController(CreateUserInteractor createUserInteractor) {
        this.createUserInteractor = createUserInteractor;
    }

    @ResponseBody
    @RequestMapping(value =  "/users", method = RequestMethod.POST,  consumes = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponse createUser(@RequestBody CreateUserRequest createUserRequest) {
        User user = createUserInteractor.create(createUserRequest.getEmail(), createUserRequest.getPassword());
        return new UserResponseFactory().build(user);
    }

}
