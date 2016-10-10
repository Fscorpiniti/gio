package ar.edu.untref.gio.validator;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Fer on 10/10/2016.
 */
public class DefaultUserValidator implements UserValidator {

    private static final String EMAIL_IS_REQUIRED = "Email is required";
    private static final String PASSWORD_IS_REQUIRED = "Password is required";

    public void validate(String email, String password) {
        if (isBlank(email)) {
            thrownIllegalArgumentException(EMAIL_IS_REQUIRED);
        }

        if (isBlank(password)) {
            thrownIllegalArgumentException(PASSWORD_IS_REQUIRED);
        }
    }

    private boolean isBlank(String field) { return StringUtils.isBlank(field); }

    private void thrownIllegalArgumentException(String message) {
        throw new IllegalArgumentException(message);
    }

}
