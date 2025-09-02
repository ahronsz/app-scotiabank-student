package com.scotiabank.service.expose.web;

import com.scotiabank.service.expose.dto.request.StudentRequest;
import com.scotiabank.service.expose.dto.response.StudentListResponse;
import com.scotiabank.service.expose.validator.ValueOfEnumStatus;
import com.scotiabank.service.student.business.StudentService;
import com.scotiabank.service.student.util.enums.StatusEnum;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public Mono<ResponseEntity<Void>> create(
            @Valid @RequestBody StudentRequest studentRequest) {
        return studentService.save(studentRequest)
                .then(Mono.just(ResponseEntity.created(URI.create("/students")).build()));
    }


    @GetMapping
    public Mono<ResponseEntity<StudentListResponse>> list(
            @Valid @RequestParam("status") @ValueOfEnumStatus(groups = StatusEnum.class) String status) {
        return studentService.getStudentsByCondition(status).map(ResponseEntity::ok);
    }

    @GetMapping("/status")
    public Mono<ResponseEntity<String>> checkStatus() {
        return Mono.just("ok").map(ResponseEntity::ok);
    }
}
