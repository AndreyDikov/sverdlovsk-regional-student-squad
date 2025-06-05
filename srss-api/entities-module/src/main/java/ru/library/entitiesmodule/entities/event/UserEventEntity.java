package ru.library.entitiesmodule.entities.event;

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
@Table(name = "users_events")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEventEntity {

    @Id
    @UuidGenerator
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    UUID uuid;

    @Column(name = "event_weight_uuid")
    UUID eventWeightUuid;

    @Column(name = "event_uuid")
    UUID eventUuid;

    @Column(name = "user_uuid")
    UUID userUuid;

    String comment;

    @Column(name = "event_rating")
    Integer eventRating;

    @Column(name = "user_score")
    Double userScore;
}
