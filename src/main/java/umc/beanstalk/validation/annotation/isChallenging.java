package umc.beanstalk.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.beanstalk.validation.validator.MissionChallengingValidator;


import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MissionChallengingValidator.class)
@Target( {ElementType.LOCAL_VARIABLE, ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface isChallenging {
    String message() default "이미 도전 중인 미션입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
