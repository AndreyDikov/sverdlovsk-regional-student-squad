package ru.application.eventmicroservice.dto;

import ru.application.eventmicroservice.enums.UserEventAction;

import java.util.UUID;

public record UserEventMessageDto(
        UUID eventUuid,
        UUID userUuid,
        UserEventAction action
) { }
