package ru.application.eventmicroservice.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(of = "uuid")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEvent {
    UUID uuid;
    UUID eventWeightUuid;
    UUID eventUuid;
    UUID userUuid;
    String comment;
    Integer eventRating;
    Double userScore;
}
