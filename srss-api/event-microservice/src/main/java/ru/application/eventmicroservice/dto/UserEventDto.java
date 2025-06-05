package ru.application.eventmicroservice.dto;

import java.util.UUID;

public record UserEventDto(
    UUID uuid,
    UUID eventWeightUuid,
    UUID eventUuid,
    UUID userUuid,
    String comment,
    Integer eventRating,
    Double userScore
) { }
