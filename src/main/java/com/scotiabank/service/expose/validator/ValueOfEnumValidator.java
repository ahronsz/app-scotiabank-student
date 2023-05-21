package com.scotiabank.service.expose.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnumStatus, CharSequence> {
    private List<String> acceptedValues;

    @Override
    public void initialize(ValueOfEnumStatus constraintAnnotation) {
        acceptedValues = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext context) {
        if (charSequence == null) {
            return true;
        }

        try {
            return acceptedValues.contains(charSequence.toString().toUpperCase());
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
