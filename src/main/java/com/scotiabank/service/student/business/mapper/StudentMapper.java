package com.scotiabank.service.student.business.mapper;

import com.scotiabank.service.expose.dto.request.StudentRequest;
import com.scotiabank.service.expose.dto.response.StudentListResponse;
import com.scotiabank.service.expose.dto.response.StudentResponse;
import com.scotiabank.service.student.model.entity.Student;
import com.scotiabank.service.student.util.enums.StatusEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class, StatusEnum.class})
public interface StudentMapper {

    @Mapping(target = "status", expression = "java(StatusEnum.findByName(request.status()))")
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    Student toEntity(StudentRequest request);

    @Mapping(target = "status", expression = "java(StatusEnum.findNameByIsActive(student.getStatus()))")
    StudentResponse toResponse(Student student);

    default StudentListResponse toStudentListResponse(List<StudentResponse> studentResponses) {
        return new StudentListResponse(studentResponses.size(), studentResponses);
    }
}
