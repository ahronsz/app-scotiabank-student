package com.scotiabank.service.expose.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValueOfEnumValidator.class)
public @interface ValueOfEnumStatus {
    String message() default "Debe ser un valor válido de ConditionEnum (activo o inactivo)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
