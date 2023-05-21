package com.scotiabank.service.student.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

//@Table(name = "Students")
@Getter
@Setter
@Builder
public class Student {
    private Long id;
    private String name;
    private String lastName;
    private Boolean condition;
    private Byte age;
    private LocalDateTime creationDateTime;
}
