package ru.application.eventmicroservice.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.application.eventmicroservice.domain.Event;
import ru.application.eventmicroservice.domain.UserEvent;
import ru.application.eventmicroservice.mappers.EventMapper;
import ru.application.eventmicroservice.mappers.UserEventMapper;
import ru.application.eventmicroservice.repositories.EventRepository;
import ru.application.eventmicroservice.repositories.UserEventRepository;
import ru.application.eventmicroservice.services.FighterEventService;
import ru.library.entitiesmodule.entities.event.EventEntity;
import ru.library.entitiesmodule.entities.event.UserEventEntity;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FighterEventServiceImpl implements FighterEventService {

    UserEventRepository userEventRepository;
    EventRepository eventRepository;

    UserEventMapper userEventMapper;
    EventMapper eventMapper;


    @Override
    public UserEvent subscribe(UUID userUuid, Event event) {
        if (event.isEventEnded()) {
            throw new RuntimeException("Event is ended");
        }

        UserEventEntity userEvent = userEventRepository.save(
                UserEventEntity.builder()
                        .eventUuid(event.getUuid())
                        .userUuid(userUuid)
                        .userScore(0.0)
                        .build()
        );

        return userEventMapper.toDomain(userEvent);
    }


    @Override
    public void unsubscribe(UUID userUuid, UUID userEventUuid) {
        UserEventEntity userEventEntity = userEventRepository.findById(userEventUuid).orElseThrow();
        EventEntity eventEntity = eventRepository.findById(userEventEntity.getEventUuid()).orElseThrow();

        Event event = eventMapper.toDomain(eventEntity);

        if (event.isEventEnded()) {
            throw new RuntimeException("Event is ended");
        }

        userEventRepository.deleteById(userEventUuid);
    }
}
