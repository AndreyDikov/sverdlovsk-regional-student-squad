package ru.application.eventmicroservice.services;

import ru.application.eventmicroservice.domain.Event;
import ru.application.eventmicroservice.enums.EventsSortedType;

import java.util.List;
import java.util.UUID;

public interface EventService {
    Event read(UUID uuid);
    List<Event> readEventsByAuthorUuid(UUID authorUuid);
    List<Event> readUserEventsHistory(UUID userUuid);
    List<Event> filter(UUID categoryUuid, EventsSortedType sortedType, int size, int page);
    List<Event> history(UUID categoryUuid, EventsSortedType sortedType, int size, int page);
}
