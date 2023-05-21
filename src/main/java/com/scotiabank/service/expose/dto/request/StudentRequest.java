package com.scotiabank.service.expose.dto.request;

import com.scotiabank.service.expose.validator.ValueOfEnumStatus;
import com.scotiabank.service.student.util.enums.ConditionEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class StudentRequest {
    @NotBlank
    @Size(min = 1, max = 255, message = "El nombre debe tener entre 1 y 255 caracteres")
    private String name;
    @NotBlank
    @Size(min = 1, max = 255, message = "El apellido debe tener entre 1 y 255 caracteres")
    private String lastName;
    @ValueOfEnumStatus(enumClass = ConditionEnum.class)
    private String condition;
    @NotNull
    @Max(value = 127, message = "La edad maxima a ingresar es de 127 años")
    @Positive(message = "La edad debe de ser de un número positivo")
    private Byte age;
}
