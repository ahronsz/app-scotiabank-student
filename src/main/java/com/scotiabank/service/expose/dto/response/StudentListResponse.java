package com.scotiabank.service.expose.dto.response;

import java.util.List;

public record StudentListResponse(
        int count,
        List<StudentResponse> students
) {}