package ar.edu.untref.gio.validator;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

public class DefaultUserValidator implements UserValidator {

    private static final String EMAIL_IS_REQUIRED = "Email is required";
    private static final String PASSWORD_IS_REQUIRED = "Password is required";

    public void execute(String email, String password) {
        validateEmail(email);
        validatePassword(password);
    }

    private void validateEmail(String email) {
        Preconditions.checkArgument(!isBlank(email), EMAIL_IS_REQUIRED);
    }

    private void validatePassword(String password) {
        Preconditions.checkArgument(!isBlank(password), PASSWORD_IS_REQUIRED);
    }

    private boolean isBlank(String field) { return StringUtils.isBlank(field); }

}
