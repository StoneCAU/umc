package umc.beanstalk.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.beanstalk.validation.validator.MembersExistValidator;


import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MembersExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistMembers {
    String message() default "해당하는 유저가 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
