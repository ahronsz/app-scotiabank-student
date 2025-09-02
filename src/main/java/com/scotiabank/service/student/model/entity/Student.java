package com.scotiabank.service.student.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "Students")
@Getter
@Setter
@Builder
public class Student {
    @Id
    private Long id;
    @Column
    private String name;
    @Column("last_name")
    private String lastName;
    @Column
    private Boolean status;
    @Column
    private Byte age;
    @Column("created_at")
    private LocalDateTime createdAt;
}
