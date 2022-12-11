package bg.softuni.exercise.annotations.email;

import bg.softuni.exercise.utils.Constants;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static bg.softuni.exercise.utils.Constants.EMAIL_VALIDATION_REGEX;

@Component
@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {

    String message() default Constants.INVALID_EMAIL_FORMAT;

    int minUserNameLength() default 1;

    int maxUserNameLength() default 50;

    int maxHostNameLength() default 50;

    String regex() default EMAIL_VALIDATION_REGEX;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
