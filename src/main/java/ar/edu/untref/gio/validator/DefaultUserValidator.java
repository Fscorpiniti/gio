package ar.edu.untref.gio.validator;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

public class DefaultUserValidator implements UserValidator {

    private static final String EMAIL_IS_REQUIRED = "Email is required";
    private static final String PASSWORD_IS_REQUIRED = "Password is required";
    public static final String NAME_IS_REQUIRED = "Name is required";

    public void execute(String email, String password, String name) {
        validateEmail(email);
        validatePassword(password);
        validateName(name);
    }

    private void validateName(String name) {
        Preconditions.checkArgument(!isBlank(name), NAME_IS_REQUIRED);
    }

    private void validateEmail(String email) {
        Preconditions.checkArgument(!isBlank(email), EMAIL_IS_REQUIRED);
    }

    private void validatePassword(String password) {
        Preconditions.checkArgument(!isBlank(password), PASSWORD_IS_REQUIRED);
    }

    private boolean isBlank(String field) { return StringUtils.isBlank(field); }

}
