package ru.library.entitiesmodule.entities.notification;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;
import ru.library.entitiesmodule.entities.notification.enums.NotificationType;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "notifications")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationEntity {

    @Id
    @UuidGenerator
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    UUID uuid;

    @Column(name = "to_user_uuid")
    UUID toUserUuid;

    @Column(name = "from_user_uuid")
    UUID fromUserUuid;

    @Enumerated(EnumType.STRING)
    NotificationType type;

    @Column(name = "created_at")
    LocalDateTime createdAt;
}
