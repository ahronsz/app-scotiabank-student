package com.scotiabank.service.student.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum ConditionEnum {

    ACTIVO("activo", Boolean.TRUE),
    INACTIVO("inactivo", Boolean.FALSE);

    private final String name;
    private final Boolean isActive;

    public static Boolean findByName(String name) {
        for (ConditionEnum value: values()) {
            if (Objects.equals(value.getName().toLowerCase(), name.toLowerCase())) {
                return value.getIsActive();
            }
        }
        return null;
    }
}
