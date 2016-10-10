package ar.edu.untref.gio.action;

import ar.edu.untref.gio.domain.User;
import ar.edu.untref.gio.domain.UserRepository;
import ar.edu.untref.gio.exception.EmailAlreadyExistentException;
import ar.edu.untref.gio.validator.DefaultUserValidator;

public class DefaultCreateUserAction implements CreateUserAction {

    private UserRepository userRepository;

    public DefaultCreateUserAction(UserRepository userRepository) {
        validateRepositoryIsNotNull(userRepository);
        this.userRepository = userRepository;
    }

    public User create(String email, String password) {
        User user = new User(email, password, new DefaultUserValidator());
        validateEmailAlreadyExistent(user.getEmail());
        userRepository.add(user);
        return user;
    }

    private void validateEmailAlreadyExistent(String email) {
        if (userRepository.exist(email)) {
            throw new EmailAlreadyExistentException("Email already existent");
        }
    }

    private void validateRepositoryIsNotNull(UserRepository userRepository) {
        if (userRepository == null) {
            throw new IllegalArgumentException("User Repository is required");
        }
    }

}