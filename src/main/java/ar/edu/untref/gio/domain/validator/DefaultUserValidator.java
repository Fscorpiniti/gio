package ar.edu.untref.gio.domain.validator;

import ar.edu.untref.gio.domain.UserEconomy;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

public class DefaultUserValidator implements UserValidator {

    private static final String EMAIL_IS_REQUIRED = "Email is required";
    private static final String PASSWORD_IS_REQUIRED = "Password is required";
    public static final String NAME_IS_REQUIRED = "Name is required";

    public void execute(String email, String password, String name, UserEconomy userEconomy) {
        Preconditions.checkArgument(!isBlank(email), EMAIL_IS_REQUIRED);
        Preconditions.checkArgument(!isBlank(password), PASSWORD_IS_REQUIRED);
        Preconditions.checkArgument(!isBlank(name), NAME_IS_REQUIRED);
        Preconditions.checkNotNull(userEconomy);
    }

    private boolean isBlank(String field) { return StringUtils.isBlank(field); }

}
