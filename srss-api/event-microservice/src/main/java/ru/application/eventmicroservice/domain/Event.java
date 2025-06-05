package ru.application.eventmicroservice.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(of = "uuid")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Event {
    UUID uuid;
    UUID authorUuid;
    UUID categoryEvaluationMethodUuid;
    String name;
    String description;
    LocalDateTime startAt;
    LocalDateTime endAt;
    String place;
    Double averageRating;
    Integer numberUsers;

    public boolean isEventEnded() {
        return LocalDateTime.now().isAfter(this.endAt);
    }
}
