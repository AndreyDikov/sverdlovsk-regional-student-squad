package ru.application.notificationmicroservice.dto;

import ru.library.entitiesmodule.entities.notification.enums.NotificationType;

import java.time.LocalDateTime;
import java.util.UUID;

public record NotificationDto (
    UUID uuid,
    UUID eventUuid,
    NotificationType type,
    LocalDateTime createdAt
) {}
