package ru.application.eventmicroservice.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.application.eventmicroservice.domain.Event;
import ru.application.eventmicroservice.enums.EventsSortedType;
import ru.application.eventmicroservice.mappers.EventMapper;
import ru.application.eventmicroservice.repositories.EventRepository;
import ru.application.eventmicroservice.services.EventService;
import ru.library.entitiesmodule.entities.event.EventEntity;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventServiceImpl implements EventService {

    EventRepository eventRepository;

    EventMapper eventMapper;


    @Override
    public Event read(UUID uuid) {
        EventEntity eventEntity = eventRepository.findById(uuid).orElseThrow();

        return eventMapper.toDomain(eventEntity);
    }


    @Override
    public List<Event> readEventsByAuthorUuid(UUID authorUuid) {
        List<EventEntity> eventEntities = eventRepository.findByAuthorUuid(authorUuid);

        return eventEntities.stream()
                .map(eventMapper::toDomain)
                .toList();
    }


    @Override
    public List<Event> readUserEventsHistory(UUID userUuid) {
        List<EventEntity> eventEntities = eventRepository.findPastEventsByUserUuid(userUuid);

        return eventEntities.stream()
                .map(eventMapper::toDomain)
                .toList();
    }


    @Override
    public List<Event> filter(
            UUID categoryUuid,
            EventsSortedType sortedType,
            int size,
            int page
    ) {
        int offset = (page - 1) * size;

        List<EventEntity> eventEntities = categoryUuid == null
                ? eventRepository.filter(sortedType.getValue(), size, offset)
                : eventRepository.filter(categoryUuid, sortedType.getValue(), size, offset);

        return eventEntities.stream()
                .map(eventMapper::toDomain)
                .toList();
    }


    @Override
    public List<Event> history(
            UUID categoryUuid,
            EventsSortedType sortedType,
            int size,
            int page
    ) {
        int offset = (page - 1) * size;

        List<EventEntity> eventEntities = categoryUuid == null
                ? eventRepository.history(sortedType.getValue(), size, offset)
                : eventRepository.history(categoryUuid, sortedType.getValue(), size, offset);

        return eventEntities.stream()
                .map(eventMapper::toDomain)
                .toList();
    }
}
