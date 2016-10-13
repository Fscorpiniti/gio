package ar.edu.untref.gio.controller;

import ar.edu.untref.gio.action.DefaultCreateUserAction;
import ar.edu.untref.gio.domain.UserRepository;
import ar.edu.untref.gio.dto.UserDTO;
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

    @Resource(name = "defaultUserRepository")
    private UserRepository userRepository;

    @ResponseBody
    @RequestMapping(value =  "/users", method = RequestMethod.POST,  consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void createUser(@RequestBody UserDTO userDTO) {
        new DefaultCreateUserAction(userRepository).create(userDTO.getEmail(), userDTO.getPassword());
    }

}
