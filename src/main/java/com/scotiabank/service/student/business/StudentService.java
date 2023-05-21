package com.scotiabank.service.student.business;

import com.scotiabank.service.expose.dto.request.StudentRequest;
import com.scotiabank.service.expose.dto.response.StudentResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {

    Mono<Void> save(Long id, StudentRequest studentRequest);
    Flux<StudentResponse> getStudentsByCondition(String codition);
}
