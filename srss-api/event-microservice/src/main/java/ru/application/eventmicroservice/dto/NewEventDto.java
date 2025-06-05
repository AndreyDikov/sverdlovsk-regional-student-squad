package ru.application.eventmicroservice.dto;

import ru.library.entitiesmodule.entities.event.enums.EvaluationMethod;

import java.util.UUID;

public record NewEventDto(
        EventDto eventDto,
        EvaluationMethod evaluationMethod,
        UUID categoryUuid
) { }
