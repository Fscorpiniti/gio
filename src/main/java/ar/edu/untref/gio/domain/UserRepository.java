package ar.edu.untref.gio.domain;

import java.util.Optional;

public interface UserRepository extends Repository<User> {

    boolean exist(String email);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Integer id);

}
