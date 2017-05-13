package ar.edu.untref.gio.domain.interactor;

import ar.edu.untref.gio.domain.User;
import ar.edu.untref.gio.domain.UserEconomy;
import ar.edu.untref.gio.domain.UserEconomyFactory;
import ar.edu.untref.gio.domain.UserRepository;
import ar.edu.untref.gio.domain.exception.EmailAlreadyExistentException;
import ar.edu.untref.gio.domain.request.CreateUserRequest;
import ar.edu.untref.gio.domain.validator.DefaultUserValidator;
import ar.edu.untref.gio.domain.validator.UserValidator;
import com.google.common.base.Preconditions;

public class DefaultCreateUserInteractor implements CreateUserInteractor {

    private static final String EMAIL_ALREADY_EXISTENT = "Ya existe un usuario con el email ingresado";
    private static final String USER_REPOSITORY_IS_REQUIRED = "User Repository is required";
    public static final String INITIAL_COINS_IS_REQUIRED = "Initial coins is required";
    private UserRepository userRepository;
    private Double initialCoins;

    public DefaultCreateUserInteractor(UserRepository userRepository, Double initialCoins) {
        Preconditions.checkNotNull(userRepository, USER_REPOSITORY_IS_REQUIRED);
        Preconditions.checkNotNull(initialCoins, INITIAL_COINS_IS_REQUIRED);
        this.userRepository = userRepository;
        this.initialCoins = initialCoins;
    }

    public User create(CreateUserRequest request) {
        User user = new User(request.getEmail(), request.getPassword(), request.getName(),
                buildUserValidator(), buildInitialEconomy());
        validateEmailAlreadyExistent(user.getEmail());
        userRepository.add(user);
        return user;
    }

    private UserValidator buildUserValidator() {
        return new DefaultUserValidator();
    }

    private UserEconomy buildInitialEconomy() {
        return new UserEconomyFactory(initialCoins).buildInitialEconomy();
    }

    private void validateEmailAlreadyExistent(String email) {
        if (userRepository.exist(email)) {
            throw new EmailAlreadyExistentException(EMAIL_ALREADY_EXISTENT);
        }
    }

}