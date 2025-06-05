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
public class EventWeight {
    UUID uuid;
    UUID categoryEvaluationMethodUuid;
    String name;
    Double weight;
}
