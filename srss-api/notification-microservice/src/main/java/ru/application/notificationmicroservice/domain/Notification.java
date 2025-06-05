package ru.application.notificationmicroservice.domain;


import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import ru.library.entitiesmodule.entities.notification.enums.NotificationType;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(of = "uuid")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Notification {
    UUID uuid;
    UUID toUserUuid;
    UUID fromUserUuid;
    UUID eventUuid;
    NotificationType type;
    LocalDateTime createdAt;
}
