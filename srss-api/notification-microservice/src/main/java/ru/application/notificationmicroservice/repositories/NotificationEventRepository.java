package ru.application.notificationmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.library.entitiesmodule.entities.notification.NotificationEventEntity;

import java.util.UUID;

@Repository
public interface NotificationEventRepository extends JpaRepository<NotificationEventEntity, UUID> {
    NotificationEventEntity findByNotificationUuid(UUID notificationUuid);
}
