package com.scotiabank.service.expose.dto.request;

import com.scotiabank.service.expose.validator.ValueOfEnumStatus;
import com.scotiabank.service.student.util.enums.StatusEnum;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record StudentRequest(
        @NotNull
        @Positive(message = "El ID debe ser un número positivo")
        @Min(value = 1, message = "El ID debe ser mayor o igual a 1")
        Long id,

        @NotBlank
        @Size(min = 1, max = 255, message = "El nombre debe tener entre 1 y 255 caracteres")
        String name,

        @NotBlank
        @Size(min = 1, max = 255, message = "El apellido debe tener entre 1 y 255 caracteres")
        String lastName,

        @ValueOfEnumStatus(groups = StatusEnum.class)
        String status,

        @NotNull
        @Max(value = 127, message = "La edad maxima a ingresar es de 127 años")
        @Positive(message = "La edad debe de ser de un número positivo")
        Byte age
) {}
