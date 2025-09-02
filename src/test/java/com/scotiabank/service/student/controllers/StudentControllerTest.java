package com.scotiabank.service.student.controllers;

import com.scotiabank.service.expose.dto.request.StudentRequest;
import com.scotiabank.service.expose.dto.response.StudentListResponse;
import com.scotiabank.service.expose.web.StudentController;
import com.scotiabank.service.student.business.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static com.scotiabank.service.student.model.MockModel.studentListResponse;
import static com.scotiabank.service.student.model.MockModel.studentRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;

    @Test
    void testCreateStudent() throws IOException {

        StudentRequest studentRequest = studentRequest();

        when(studentService.save(any(StudentRequest.class))).thenReturn(Mono.empty());

        StepVerifier.create(studentController.create(studentRequest))
                .expectNextMatches(resp -> resp.getStatusCode().is2xxSuccessful())
                .verifyComplete();

        verify(studentService, times(1)).save(studentRequest);
    }

    @Test
    void testRetrieveStudents() throws IOException {

        StudentListResponse studentListResponse = studentListResponse();

        when(studentService.getStudentsByCondition(anyString())).thenReturn(Mono.just(studentListResponse));

        StepVerifier.create(studentController.list("active"))
                .expectNextMatches(resp -> resp.getStatusCode().is2xxSuccessful())
                .verifyComplete();

        verify(studentService, times(1)).getStudentsByCondition("active");
    }
}
