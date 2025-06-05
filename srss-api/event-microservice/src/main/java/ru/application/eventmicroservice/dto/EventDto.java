package ru.application.eventmicroservice.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventDto(
        UUID uuid,
        UUID authorUuid,
        UUID categoryEvaluationMethodUuid,
        String name,
        String description,
        LocalDateTime startAt,
        LocalDateTime endAt,
        String place,
        Double averageRating,
        Integer numberUsers
) { }
