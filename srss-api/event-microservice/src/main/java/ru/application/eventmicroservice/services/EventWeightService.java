package ru.application.eventmicroservice.services;

import ru.application.eventmicroservice.domain.EventWeight;

import java.util.List;
import java.util.UUID;

public interface EventWeightService {
    List<EventWeight> readWeightsByCategoryEvaluationMethodUuid(UUID categoryEvaluationMethodUuid);
    List<EventWeight> readWeightsByCategoryUuid(UUID categoryUuid);
}
