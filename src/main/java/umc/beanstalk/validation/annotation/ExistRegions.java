package umc.beanstalk.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.beanstalk.validation.validator.RegionsExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RegionsExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistRegions {
    String message() default "해당하는 지역이 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
