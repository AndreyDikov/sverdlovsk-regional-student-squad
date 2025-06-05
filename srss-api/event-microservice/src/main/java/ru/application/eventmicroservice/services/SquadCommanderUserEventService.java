package ru.application.eventmicroservice.services;

import ru.application.eventmicroservice.domain.UserEvent;

import java.util.UUID;

public interface SquadCommanderUserEventService {
    UserEvent subscribeUserToEvent(UUID eventUuid, UUID userUuid);
    void unsubscribeUserFromEvent(UUID eventUuid, UUID userUuid);
}
