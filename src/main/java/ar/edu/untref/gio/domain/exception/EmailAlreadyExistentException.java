package ar.edu.untref.gio.domain.exception;

public class EmailAlreadyExistentException extends RuntimeException {

    public EmailAlreadyExistentException(String message) {
        super(message);
    }

}
