package com.scotiabank.service.expose.dto.response;

public record StudentResponse(
        Long id,
        String name,
        String lastName,
        String status,
        Byte age,
        String createdAt
) {}
