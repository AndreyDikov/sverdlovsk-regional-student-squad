package ru.application.eventmicroservice.services;

import ru.application.eventmicroservice.domain.UserEvent;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface UserEventService {
    List<UserEvent> readUserEventsByUserUuid(UUID userUuid);
    List<UserEvent> readUsersEventByEventUuid(UUID eventUuid);
    Double calculateUserScore(UUID userUuid);
    Double calculateSquadScore(Set<UUID> squad);
}
