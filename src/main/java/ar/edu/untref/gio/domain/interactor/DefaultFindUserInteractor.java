package ar.edu.untref.gio.domain.interactor;

import ar.edu.untref.gio.domain.User;
import ar.edu.untref.gio.domain.UserRepository;

import java.util.Optional;

public class DefaultFindUserInteractor implements FindUserInteractor {

    private UserRepository userRepository;

    public DefaultFindUserInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }
}
