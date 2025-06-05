package ru.application.eventmicroservice.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.application.eventmicroservice.domain.EventWeight;
import ru.application.eventmicroservice.mappers.EventWeightMapper;
import ru.application.eventmicroservice.repositories.EventWeightRepository;
import ru.application.eventmicroservice.repositories.UserEventRepository;
import ru.application.eventmicroservice.services.StaffCommanderEventWeightService;
import ru.library.entitiesmodule.entities.event.EventWeightEntity;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StaffCommanderEventWeightServiceImpl implements StaffCommanderEventWeightService {

    EventWeightRepository eventWeightRepository;
    UserEventRepository userEventRepository;

    EventWeightMapper eventWeightMapper;


    @Override
    public void setWeightByUuid(
            UUID uuid,
            Double weight
    ) {
        EventWeightEntity eventWeightEntity = eventWeightRepository
                .findById(uuid)
                .orElseThrow();

        EventWeight eventWeight = eventWeightMapper.toDomain(eventWeightEntity);

        userEventRepository.updateUserScores(uuid, eventWeight.getWeight(), weight);
        eventWeightRepository.setWeightByUuid(uuid, weight);
    }
}
