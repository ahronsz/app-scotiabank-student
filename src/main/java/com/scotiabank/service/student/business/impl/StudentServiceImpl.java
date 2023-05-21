package com.scotiabank.service.student.business.impl;

import com.scotiabank.service.expose.dto.request.StudentRequest;
import com.scotiabank.service.expose.dto.response.StudentResponse;
import com.scotiabank.service.student.business.StudentService;
import com.scotiabank.service.student.business.mapper.StudentMapper;
import com.scotiabank.service.student.repository.StudentRepository;
import com.scotiabank.service.student.util.enums.ConditionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper mapper;

    @Override
    public Mono<Void> save(Long id, StudentRequest studentRequest) {
        return studentRepository.findById(id)
                .hasElement()
                .flatMap(found -> {
                    if (found) {
                        return Mono.error(new ResponseStatusException(HttpStatus.CONFLICT, "Usuario ya registrado"));
                    } else {
                        return studentRepository.save(mapper.toEntity(id, studentRequest)).then();
                    }
                });
    }

    @Override
    public Flux<StudentResponse> getStudentsByCondition(String codition) {
        if (codition != null) {
            return studentRepository.getStudentsByCondition(ConditionEnum.findByName(codition))
                    .map(mapper::toResponse);
        } else {
            return studentRepository.findAll().map(mapper::toResponse);
        }
    }
}
