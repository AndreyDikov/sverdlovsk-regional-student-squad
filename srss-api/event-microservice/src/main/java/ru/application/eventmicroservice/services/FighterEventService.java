package ru.application.eventmicroservice.services;

import ru.application.eventmicroservice.domain.Event;
import ru.application.eventmicroservice.domain.UserEvent;

import java.util.UUID;

public interface FighterEventService {
    UserEvent subscribe(UUID userUuid, Event event);
    void unsubscribe(UUID userUuid, UUID userEventUuid);
}
