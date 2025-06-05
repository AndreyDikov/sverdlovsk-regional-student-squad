package ru.application.eventmicroservice.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import ru.library.entitiesmodule.entities.event.enums.EvaluationMethod;

import java.util.UUID;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(of = "uuid")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryEvaluationMethod {
    UUID uuid;
    UUID categoryUuid;
    EvaluationMethod evaluationMethod;
}
