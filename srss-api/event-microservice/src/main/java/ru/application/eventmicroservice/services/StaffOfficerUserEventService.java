package ru.application.eventmicroservice.services;

import ru.application.eventmicroservice.domain.UserEvent;

import java.util.UUID;

public interface StaffOfficerUserEventService {
    UserEvent setUserScore(UUID userEventUuid, UUID eventWeightUuid, Integer score);
}
