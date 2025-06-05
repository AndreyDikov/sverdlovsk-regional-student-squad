package ru.application.eventmicroservice.services;

import ru.application.eventmicroservice.domain.Event;
import ru.library.entitiesmodule.entities.event.enums.EvaluationMethod;

import java.util.UUID;

public interface StaffOfficerEventService {
    Event create(
            UUID userUuid,
            UUID categoryUuid,
            EvaluationMethod evaluationMethod,
            Event event
    );

    Event update(
            UUID userUuid,
            UUID categoryUuid,
            EvaluationMethod evaluationMethod,
            Event event
    );

    void deleteEventByUuid(UUID eventUuid);
}
