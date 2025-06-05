package ru.application.bff.srssbff.dto;

import org.springframework.http.HttpMethod;

public record OperationDto (
        HttpMethod method,
        String url,
        Object body
) { }
