package ar.edu.untref.gio.presentation.controller;

import ar.edu.untref.gio.domain.User;
import ar.edu.untref.gio.domain.exception.UserNotFoundException;
import ar.edu.untref.gio.domain.interactor.CreateUserInteractor;
import ar.edu.untref.gio.domain.interactor.FindUserInteractor;
import ar.edu.untref.gio.domain.request.CreateUserRequest;
import ar.edu.untref.gio.domain.service.ExistTokenService;
import ar.edu.untref.gio.presentation.response.ApiError;
import ar.edu.untref.gio.presentation.response.UserResponse;
import ar.edu.untref.gio.presentation.response.UserResponseFactory;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Optional;

@Controller
@Transactional
public class UserController {

    private static final String AUTH_TOKEN = "auth_token";

    @Resource(name = "createUserInteractor")
    private CreateUserInteractor createUserInteractor;

    @Resource(name = "findUserInteractor")
    private FindUserInteractor findUserInteractor;

    @Resource(name = "existTokenService")
    private ExistTokenService existTokenService;

    @ResponseBody
    @ApiOperation(value = "Creacion de usuarios")
    @RequestMapping(value =  "/users", method = RequestMethod.POST,  consumes = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponse createUser(@RequestBody CreateUserRequest createUserRequest) {
        User user = createUserInteractor.create(createUserRequest);
        return new UserResponseFactory().build(user);
    }

    @ResponseBody
    @ApiOperation(value = "Obtener usuario por id del mismo")
    @RequestMapping(value =  "/users/{id}", method = RequestMethod.GET)
    public UserResponse findUserById(@PathVariable Integer id, @RequestHeader(value = AUTH_TOKEN) String authToken) {
        this.existTokenService.exist(id, authToken);
        Optional<User> user = findUserInteractor.findById(id);
        return new UserResponseFactory().build(user.orElseThrow(UserNotFoundException::new));
    }

    @ResponseBody
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ RuntimeException.class, Exception.class })
    public ApiError handleError(HttpServletRequest request, Throwable exception) {
        return new ApiError(exception.getMessage());
    }

}
