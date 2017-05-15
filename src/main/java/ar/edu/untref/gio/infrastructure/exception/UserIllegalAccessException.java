package ar.edu.untref.gio.infrastructure.exception;

public class UserIllegalAccessException extends RuntimeException {

    private static final String ILLEGAL_ACCESS = "Illegal access";

    public UserIllegalAccessException() {
        super(ILLEGAL_ACCESS);
    }

}
