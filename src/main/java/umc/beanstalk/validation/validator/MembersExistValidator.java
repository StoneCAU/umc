package umc.beanstalk.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.beanstalk.apiPayload.code.status.ErrorStatus;
import umc.beanstalk.repository.MemberRepository;
import umc.beanstalk.validation.annotation.ExistMembers;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MembersExistValidator implements ConstraintValidator<ExistMembers, Long> {

    private final MemberRepository memberRepository;

    @Override
    public void initialize(ExistMembers constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        boolean isValid = memberRepository.existsById(value);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
