package ru.library.entitiesmodule.entities.event;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(
        name = "events",
        indexes = {
                @Index(name = "idx_events_start_at", columnList = "start_at"),
                @Index(name = "idx_events_end_at", columnList = "end_at"),
                @Index(name = "idx_events_num_users", columnList = "number_users"),
                @Index(name = "idx_events_name", columnList = "name")
        }
)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventEntity {

    @Id
    @UuidGenerator
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    UUID uuid;

    @Column(name = "author_uuid")
    UUID authorUuid;

    @Column(name = "category_evaluation_method_uuid")
    UUID categoryEvaluationMethodUuid;

    String name;

    //todo: здесь должно быть поле с картинкой мероприятия

    String description;

    @Column(name = "start_at")
    LocalDateTime startAt;

    @Column(name = "end_at")
    LocalDateTime endAt;

    String place;

    @Column(name = "average_rating")
    Double averageRating;

    @Column(name = "number_users")
    Integer numberUsers;
}
