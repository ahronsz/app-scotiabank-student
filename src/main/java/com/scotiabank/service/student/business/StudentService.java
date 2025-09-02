package com.scotiabank.service.student.business;

import com.scotiabank.service.expose.dto.request.StudentRequest;
import com.scotiabank.service.expose.dto.response.StudentListResponse;
import reactor.core.publisher.Mono;

public interface StudentService {
    Mono<Void> save(StudentRequest studentRequest);
    Mono<StudentListResponse> getStudentsByCondition(String status);
}
