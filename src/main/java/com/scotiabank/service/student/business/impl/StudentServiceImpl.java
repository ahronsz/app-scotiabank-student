package com.scotiabank.service.student.business.impl;

import com.scotiabank.service.expose.dto.request.StudentRequest;
import com.scotiabank.service.expose.dto.response.StudentListResponse;
import com.scotiabank.service.student.business.StudentService;
import com.scotiabank.service.student.business.mapper.StudentMapper;
import com.scotiabank.service.student.exception.ConflictException;
import com.scotiabank.service.student.model.entity.Student;
import com.scotiabank.service.student.repository.StudentRepository;
import com.scotiabank.service.student.util.enums.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final R2dbcEntityTemplate template;
    private final StudentMapper studentMapper;

    @Override
    public Mono<Void> save(StudentRequest studentRequest) {
        return studentRepository.findById(studentRequest.id())
                .flatMap(existingStudent -> Mono.error(new ConflictException("El estudiante con el id " + studentRequest.id() + " ya existe."))) // Si existe, lanzamos un error
                .switchIfEmpty(create(studentMapper.toEntity(studentRequest))).then();
    }

    @Override
    public Mono<StudentListResponse> getStudentsByCondition(String status) {
        return studentRepository.findByStatus(StatusEnum.findByName(status))
                .map(studentMapper::toResponse)
                .collectList()
                .map(studentMapper::toStudentListResponse);
    }

    private Mono<Void> create(Student student) {
        return template.insert(Student.class)
                .using(student)
                .then();
    }

}
