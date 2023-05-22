package com.scotiabank.service.expose;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.scotiabank.service.expose.dto.request.StudentRequest;
import com.scotiabank.service.expose.dto.response.StudentResponse;
import com.scotiabank.service.expose.web.StudentController;
import com.scotiabank.service.student.business.StudentService;
import com.scotiabank.service.student.util.enums.ConditionEnum;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Objects;

@ExtendWith(MockitoExtension.class)
@WebFluxTest(controllers = StudentController.class)
@ContextConfiguration(classes = {StudentController.class})
public class StudentControllerTest {

    @MockBean
    private StudentService studentService;

    @Autowired
    private WebTestClient webTestClient;

    private StudentRequest studentRequest;
    private StudentResponse studentResponse;

    @BeforeEach
    public void setUp() {
        studentRequest = StudentRequest.builder()
                .name("Ahron")
                .lastName("Sotomayor Zegarra")
                .condition(ConditionEnum.ACTIVO.name())
                .condition("ACTIVO")
                .age((byte) 27)
                .build();
        studentResponse = StudentResponse.builder()
                .id(1L)
                .name("Ahron")
                .lastName("Sotomayor Zegarra")
                .condition("ACTIVO")
                .condition(ConditionEnum.ACTIVO.name())
                .age((byte) 27)
                .build();
    }

    @Test
    @DisplayName("Crea un estudiante")
    void createStudent() {
        when(studentService.save(anyLong(), any(StudentRequest.class))).thenReturn(Mono.empty());
        webTestClient.post()
                .uri("/students/{id}", Collections.singletonMap("id", 1L))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(studentRequest), StudentRequest.class)
                .exchange()
                .expectStatus()
                .isCreated();
        verify(studentService, times(1)).save(anyLong(), any(StudentRequest.class));
    }

    @Test
    @DisplayName("Lista todos los estudiantes con el estado de Activo")
    void listStudents() {
        when(studentService.getStudentsByCondition(anyString())).thenReturn(Flux.just(studentResponse));
        webTestClient.get()
                .uri("/students?condition=ACTIVO")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(StudentResponse.class)
                .consumeWith(result -> {
                    String nameResult = Objects.requireNonNull(result.getResponseBody()).get(0).getName();
                    Assertions.assertThat(nameResult).isEqualTo("Ahron");
                });
        verify(studentService, times(1)).getStudentsByCondition(anyString());
    }

}
