package ru.application.usermicroservice.domain;

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
public class Squad {
    UUID uuid;
    UUID commanderUuid;
    String name;
    String description;
    Double score;
    Boolean isClosed;
    Integer numberUsers;
}
