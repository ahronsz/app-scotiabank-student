package com.scotiabank.service.student.repository;

import com.scotiabank.service.student.model.entity.Student;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class StudentRepository {
    private final List<Student> students = new ArrayList<>();

    public Mono<Student> save(Student student) {
        students.add(student);
        return Mono.just(student);
    }

    public Flux<Student> findAll() {
        return Flux.fromIterable(students);
    }

    public Mono<Student> findById(Long id) {
        return Flux.fromIterable(students)
                .filter(student -> Objects.equals(student.getId(), id))
                .next();
    }

    public Flux<Student> getStudentsByCondition(Boolean isActive) {
        return Flux.fromIterable(students)
                .filter(student -> student.getCondition() == isActive);
    }
}
