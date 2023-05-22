package com.scotiabank.service.student.business.mapper;

import com.scotiabank.service.expose.dto.request.StudentRequest;
import com.scotiabank.service.expose.dto.response.StudentResponse;
import com.scotiabank.service.student.model.entity.Student;
import com.scotiabank.service.student.util.enums.ConditionEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class, ConditionEnum.class})
public interface StudentMapper {

    @Mapping(target = "condition", expression = "java(ConditionEnum.findByName(request.getCondition()))")
    @Mapping(target = "creationDateTime", expression = "java(LocalDateTime.now())")
    Student toEntity(Long id, StudentRequest request);

    @Mapping(target = "condition", expression = "java(ConditionEnum.findByName(student.getCondition()))")
    StudentResponse toResponse(Student student);
}
