package ru.application.usermicroservice.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum SquadsSortedType {

    RATING_ASC("score ASC"),

    RATING_DESC("score DESC"),

    NUMBER_USERS_ASC("number_users ASC"),

    NUMBER_USERS_DESC("number_users DESC"),

    ALPHABET("name ASC");

    String value;
}
