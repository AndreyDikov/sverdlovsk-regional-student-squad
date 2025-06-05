package ru.application.notificationmicroservice.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import ru.application.notificationmicroservice.enums.UserEventAction;

import java.util.UUID;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEventMessage {
    UUID eventUuid;
    UUID userUuid;
    UserEventAction action;
}
