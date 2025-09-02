package com.scotiabank.service.expose.validator;

import com.scotiabank.service.student.util.enums.StatusEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnumStatus, String> {

    @Override
    public void initialize(ValueOfEnumStatus constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && StatusEnum.findByName(s) != null;
    }
}
