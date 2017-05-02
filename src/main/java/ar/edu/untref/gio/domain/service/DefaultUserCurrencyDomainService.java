package ar.edu.untref.gio.domain.service;

import ar.edu.untref.gio.domain.User;
import ar.edu.untref.gio.domain.UserCurrencyOperation;
import ar.edu.untref.gio.domain.exception.UserNotFoundException;

import java.util.Optional;

public class DefaultUserCurrencyDomainService implements UserCurrencyDomainService {

    @Override
    public void execute(UserCurrencyOperation userCurrencyOperation, Optional<User> user) {
        userCurrencyOperation.execute(user.orElseThrow(UserNotFoundException::new));
    }

}
