package ru.application.eventmicroservice.dto;

import java.util.UUID;

public record EventWeightDto(
    UUID uuid,
    UUID categoryEvaluationMethodUuid,
    String name,
    Double weight
) { }
