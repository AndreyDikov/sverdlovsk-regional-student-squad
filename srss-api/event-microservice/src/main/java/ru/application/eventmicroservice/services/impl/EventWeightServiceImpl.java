package ru.application.eventmicroservice.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.application.eventmicroservice.domain.EventWeight;
import ru.application.eventmicroservice.mappers.EventWeightMapper;
import ru.application.eventmicroservice.repositories.EventWeightRepository;
import ru.application.eventmicroservice.services.EventWeightService;
import ru.library.entitiesmodule.entities.event.EventWeightEntity;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventWeightServiceImpl implements EventWeightService {

    EventWeightRepository eventWeightRepository;

    EventWeightMapper eventWeightMapper;


    @Override
    public List<EventWeight> readWeightsByCategoryEvaluationMethodUuid(
            UUID categoryEvaluationMethodUuid
    ) {
        List<EventWeightEntity> eventWeightEntities = eventWeightRepository
                .findByCategoryEvaluationMethodUuid(categoryEvaluationMethodUuid);

        return eventWeightEntities.stream()
                .map(eventWeightMapper::toDomain)
                .toList();
    }


    @Override
    public List<EventWeight> readWeightsByCategoryUuid(
            UUID categoryUuid
    ) {
        List<EventWeightEntity> eventWeightEntities = eventWeightRepository
                .findByCategoryUuid(categoryUuid);

        return eventWeightEntities.stream()
                .map(eventWeightMapper::toDomain)
                .toList();
    }
}
