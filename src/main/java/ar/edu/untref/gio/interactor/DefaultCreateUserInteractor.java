package ar.edu.untref.gio.interactor;

import ar.edu.untref.gio.domain.User;
import ar.edu.untref.gio.domain.UserRepository;
import ar.edu.untref.gio.exception.EmailAlreadyExistentException;
import ar.edu.untref.gio.validator.DefaultUserValidator;

public class DefaultCreateUserInteractor implements CreateUserInteractor {

    private static final String EMAIL_ALREADY_EXISTENT = "Email already existent";
    private static final String USER_REPOSITORY_IS_REQUIRED = "User Repository is required";
    private UserRepository userRepository;

    public DefaultCreateUserInteractor(UserRepository userRepository) {
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
            throw new EmailAlreadyExistentException(EMAIL_ALREADY_EXISTENT);
        }
    }

    private void validateRepositoryIsNotNull(UserRepository userRepository) {
        if (userRepository == null) {
            throw new IllegalArgumentException(USER_REPOSITORY_IS_REQUIRED);
        }
    }

}