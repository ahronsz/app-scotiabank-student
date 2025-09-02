package com.scotiabank.service.student.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum StatusEnum {

    ACTIVO("activo", Boolean.TRUE),
    INACTIVO("inactivo", Boolean.FALSE);

    private final String name;
    private final Boolean status;

    public static Boolean findByName(String name) {
        for (StatusEnum value: values()) {
            if (Objects.equals(value.getName().toLowerCase(), name.toLowerCase())) {
                return value.getStatus();
            }
        }
        return null;
    }

    public static String findNameByIsActive(Boolean isActive) {
        for (StatusEnum value : values()) {
            if (Objects.equals(value.getStatus(), isActive)) {
                return value.getName();
            }
        }
        return null;
    }
}
