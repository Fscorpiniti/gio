package ar.edu.untref.gio.domain;

public interface UserRepository {

    void add(User user);
    boolean exist(String email);

}
