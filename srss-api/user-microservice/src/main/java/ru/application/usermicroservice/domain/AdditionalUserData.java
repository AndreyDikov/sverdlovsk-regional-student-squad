package ru.application.usermicroservice.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import ru.library.entitiesmodule.entities.user.enums.Gender;

import java.util.UUID;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(of = "uuid")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdditionalUserData {
    UUID uuid;
    UUID userUuid;
    UUID squadUuid;
    Gender gender;
    Double score;
}
