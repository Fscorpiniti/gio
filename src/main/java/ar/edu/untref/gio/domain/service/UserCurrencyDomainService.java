package ar.edu.untref.gio.domain.service;

import ar.edu.untref.gio.domain.User;
import ar.edu.untref.gio.domain.UserCurrencyOperation;

import java.util.Optional;

public interface UserCurrencyDomainService {

    void execute(UserCurrencyOperation userCurrencyOperation, Optional<User> user);

}
