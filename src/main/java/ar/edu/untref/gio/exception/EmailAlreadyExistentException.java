package ar.edu.untref.gio.exception;

public class EmailAlreadyExistentException extends RuntimeException {

    public EmailAlreadyExistentException(String message) {
        super(message);
    }

}
