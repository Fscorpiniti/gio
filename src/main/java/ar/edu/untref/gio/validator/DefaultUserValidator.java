package ar.edu.untref.gio.validator;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Fer on 10/10/2016.
 */
public class DefaultUserValidator implements UserValidator {

    public void validate(String email, String password) {
        if (StringUtils.isBlank(email)) {
            thrownIllegalArgumentException("Email is required");
        }

        if (StringUtils.isBlank(password)) {
            thrownIllegalArgumentException("Password is required");
        }
    }

    private void thrownIllegalArgumentException(String message) {
        throw new IllegalArgumentException(message);
    }

}
