package ru.application.notificationmicroservice.mappers;

import org.mapstruct.Mapper;
import ru.application.notificationmicroservice.domain.Notification;
import ru.application.notificationmicroservice.dto.NotificationDto;
import ru.library.entitiesmodule.entities.notification.NotificationEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationDto toDto(Notification domain);
    List<NotificationDto> toDtoList(List<Notification> domains);
    Notification toDomain(NotificationEntity entity);
}
