package com.scotiabank.service.student.repository;

import com.scotiabank.service.student.model.entity.Student;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface StudentRepository extends R2dbcRepository<Student, Long> {
    Flux<Student> findByStatus(Boolean status);
}
