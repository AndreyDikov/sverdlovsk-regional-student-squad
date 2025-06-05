package ru.application.notificationmicroservice.services;

import ru.application.notificationmicroservice.domain.Notification;

import java.util.List;
import java.util.UUID;

public interface NotificationService {
    List<Notification> getNotificationsForUser(UUID userUuid);
}
