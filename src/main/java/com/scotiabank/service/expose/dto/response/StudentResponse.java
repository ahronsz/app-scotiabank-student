package com.scotiabank.service.expose.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StudentResponse {
    private Long id;
    private String name;
    private String lastName;
    private String condition;
    private Byte age;
    private String creationDateTime;
}
