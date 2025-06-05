package ru.application.eventmicroservice.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.application.eventmicroservice.domain.UserEvent;
import ru.application.eventmicroservice.mappers.UserEventMapper;
import ru.application.eventmicroservice.repositories.UserEventRepository;
import ru.application.eventmicroservice.services.UserEventService;
import ru.library.entitiesmodule.entities.event.UserEventEntity;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserEventServiceImpl implements UserEventService {

    UserEventRepository userEventRepository;

    UserEventMapper userEventMapper;


    @Override
    public List<UserEvent> readUserEventsByUserUuid(UUID userUuid) {
        List<UserEventEntity> userEventEntities = userEventRepository.findByUserUuid(userUuid);

        return userEventEntities.stream()
                .map(userEventMapper::toDomain)
                .toList();
    }


    @Override
    public List<UserEvent> readUsersEventByEventUuid(UUID eventUuid) {
        List<UserEventEntity> userEventEntities = userEventRepository.findByEventUuid(eventUuid);

        return userEventEntities.stream()
                .map(userEventMapper::toDomain)
                .toList();
    }


    @Override
    public Double calculateUserScore(UUID userUuid) {
        return userEventRepository.calculateUserScore(userUuid);
    }


    @Override
    public Double calculateSquadScore(Set<UUID> squad) {
        return userEventRepository.calculateSquadScore(squad);
    }
}
