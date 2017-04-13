package ar.edu.untref.gio.presentation.response;

import ar.edu.untref.gio.domain.User;

public class UserResponseFactory {

    public UserResponse build(User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getName());
    }

}