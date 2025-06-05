package ru.application.eventmicroservice.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.application.eventmicroservice.domain.EventWeight;
import ru.application.eventmicroservice.domain.UserEvent;
import ru.application.eventmicroservice.mappers.EventWeightMapper;
import ru.application.eventmicroservice.mappers.UserEventMapper;
import ru.application.eventmicroservice.repositories.EventWeightRepository;
import ru.application.eventmicroservice.repositories.UserEventRepository;
import ru.application.eventmicroservice.services.StaffOfficerUserEventService;
import ru.library.entitiesmodule.entities.event.EventWeightEntity;
import ru.library.entitiesmodule.entities.event.UserEventEntity;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StaffOfficerUserEventServiceImpl implements StaffOfficerUserEventService {

    UserEventRepository userEventRepository;
    EventWeightRepository eventWeightRepository;

    UserEventMapper userEventMapper;
    EventWeightMapper eventWeightMapper;


    @Override
    public UserEvent setUserScore(
            UUID userEventUuid,
            UUID eventWeightUuid,
            Integer score
    ) {
        EventWeightEntity eventWeightEntity = eventWeightRepository
                .findById(eventWeightUuid)
                .orElse(null);

        EventWeight eventWeight = eventWeightMapper == null
                ? null
                : eventWeightMapper.toDomain(eventWeightEntity);

        UserEventEntity userEventEntity = userEventRepository
                .findById(userEventUuid)
                .orElseThrow();

        userEventEntity.setEventWeightUuid(eventWeight == null ? null : eventWeight.getUuid());
        userEventEntity.setUserScore(eventWeight == null ? null : eventWeight.getWeight() * score);

        UserEventEntity savedUserEventEntity = userEventRepository.save(userEventEntity);

        return userEventMapper.toDomain(savedUserEventEntity);
    }
}
