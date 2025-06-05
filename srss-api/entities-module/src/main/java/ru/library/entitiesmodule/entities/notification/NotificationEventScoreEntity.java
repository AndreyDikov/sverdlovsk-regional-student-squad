package ru.library.entitiesmodule.entities.notification;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "notifications_events_scores")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationEventScoreEntity {

    @Id
    @UuidGenerator
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    UUID uuid;

    Double score;

    @Column(name = "notification_event_uuid")
    UUID notificationEventUuid;
}
