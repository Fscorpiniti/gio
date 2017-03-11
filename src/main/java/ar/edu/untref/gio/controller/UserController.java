package ar.edu.untref.gio.controller;

import ar.edu.untref.gio.interactor.CreateUserInteractor;
import ar.edu.untref.gio.dto.CreateUserDTO;
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
    public void createUser(@RequestBody CreateUserDTO createUserDTO) {
        createUserInteractor.create(createUserDTO.getEmail(), createUserDTO.getPassword());
    }

}
