package ar.edu.untref.gio.domain;

public interface UserRepository extends Repository<User> {

    boolean exist(String email);

}
