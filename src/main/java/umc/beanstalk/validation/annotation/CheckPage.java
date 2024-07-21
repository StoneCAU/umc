package umc.beanstalk.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.beanstalk.validation.validator.CheckPageValidator;


import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CheckPageValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPage {
    String message() default "page의 범위가 작습니다. 1 이상 입력하세요.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
