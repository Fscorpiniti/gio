package ar.edu.untref.gio.domain.exception;

public class InsufficientCurrenciesException extends RuntimeException {

    public static final String MESSAGE = "Monedas insuficientes para crear plazo fijo.";

    public InsufficientCurrenciesException() {
        super(MESSAGE);
    }
}
