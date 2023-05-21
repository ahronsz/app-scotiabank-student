package com.scotiabank.service.expose.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = ValueOfEnumValidator.class)
@Target({ ElementType.PARAMETER, ElementType.FIELD, ElementType.TYPE_PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValueOfEnumStatus {
    String message() default "el parametro/campo condition debe ser ACTIVO o INACTIVO";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends Enum<?>> enumClass();
}
