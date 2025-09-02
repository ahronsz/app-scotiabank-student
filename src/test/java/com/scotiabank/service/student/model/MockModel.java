package com.scotiabank.service.student.model;

import com.scotiabank.service.expose.dto.request.StudentRequest;
import com.scotiabank.service.expose.dto.response.StudentListResponse;
import com.scotiabank.service.student.util.JsonUtils;

import java.io.IOException;

public class MockModel {

    public static StudentRequest studentRequest() throws IOException {
        return JsonUtils.readJsonFromClasspath("mock/student-request.json", StudentRequest.class);
    }

    public static StudentListResponse studentListResponse() throws IOException {
        return JsonUtils.readJsonFromClasspath("mock/student-list-response.json", StudentListResponse.class);
    }
}
