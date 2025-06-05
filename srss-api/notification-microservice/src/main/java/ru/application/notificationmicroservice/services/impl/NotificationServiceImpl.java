package ru.application.notificationmicroservice.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.application.notificationmicroservice.domain.Notification;
import ru.application.notificationmicroservice.mappers.NotificationMapper;
import ru.application.notificationmicroservice.repositories.NotificationEventRepository;
import ru.application.notificationmicroservice.repositories.NotificationRepository;
import ru.application.notificationmicroservice.services.NotificationService;
import ru.library.entitiesmodule.entities.notification.NotificationEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationServiceImpl implements NotificationService {

    NotificationRepository notificationRepository;
    NotificationEventRepository notificationEventRepository;

    NotificationMapper notificationMapper;

    @Override
    public List<Notification> getNotificationsForUser(UUID userUuid) {
        List<NotificationEntity> entities =
                notificationRepository.findByToUserUuidOrderByCreatedAtDesc(userUuid);

        return entities.stream()
                .map(entity -> {
                    UUID eventUuid = notificationEventRepository
                            .findByNotificationUuid(entity.getUuid())
                            .getEventUuid();
                    Notification domain = notificationMapper.toDomain(entity);
                    domain.setEventUuid(eventUuid);
                    return domain;
                })
                .collect(Collectors.toList());
    }
}
