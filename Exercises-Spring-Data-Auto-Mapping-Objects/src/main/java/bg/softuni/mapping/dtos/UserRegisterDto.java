package bg.softuni.mapping.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

import static bg.softuni.mapping.constants.Validations.*;

@Data
@NoArgsConstructor
@Builder
public class UserRegisterDto {

    private String email;

    private String password;

    private String confirmPassword;

    private String fullName;

    public UserRegisterDto(String email, String password, String confirmPassword, String fullName) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
        validate();
    }

    private void validate() {
        boolean isEmailValid = Pattern.matches(EMAIL_PATTERN, this.email);

        //validate email
        if(!isEmailValid) {
            throw new IllegalArgumentException(EMAIL_NOT_VALID_MESSAGE);
        }

        boolean isPasswordValid = Pattern.matches(PASSWORD_PATTERN, this.password);

        //validate password
        if(!isPasswordValid) {
            throw new IllegalArgumentException(PASSWORD_NOT_VALID_MESSAGE);
        }

        // check password and confirm password match
        if(!password.equals(confirmPassword)) {
            throw new IllegalArgumentException(PASSWORD_MISS_MATCH_MESSAGE);
        }
    }
}
