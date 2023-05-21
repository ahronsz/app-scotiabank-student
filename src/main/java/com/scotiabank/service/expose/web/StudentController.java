package com.scotiabank.service.expose.web;

import com.scotiabank.service.expose.dto.request.StudentRequest;
import com.scotiabank.service.expose.dto.response.StudentResponse;
import com.scotiabank.service.expose.validator.ValueOfEnumStatus;
import com.scotiabank.service.student.business.StudentService;
import com.scotiabank.service.student.util.enums.ConditionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public Mono<Void> create(
            @NotNull @Positive @PathVariable("id") Long id,
            @Valid @RequestBody StudentRequest studentRequest) {
        return studentService.save(id, studentRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Flux<StudentResponse> list(
            @Param("condition")
            @ValueOfEnumStatus(enumClass = ConditionEnum.class) String condition) {
        return studentService.getStudentsByCondition(condition)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT)));
    }

    // Exception handler for ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleConstraintViolationException(ConstraintViolationException ex) {
        return "Invalid request: " + ex.getMessage();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/status")
    public String checkStatus() {
        return "ok";
    }
}
