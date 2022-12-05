package bg.softuni.mapping.constants;

public class Validations {
    ;
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";

    public static final String EMAIL_NOT_VALID_MESSAGE = "Incorrect email.";
    public static final String PASSWORD_NOT_VALID_MESSAGE = "Incorrect username / password";

    public static final String PASSWORD_NOT_VALID_LOGIN_MESSAGE = "Incorrect email / password";
    public static final String PASSWORD_MISS_MATCH_MESSAGE = "Passwords are not matching";

    public static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$";

    public static final String COMMAND_NOT_FOUND = "Command not found";

}
