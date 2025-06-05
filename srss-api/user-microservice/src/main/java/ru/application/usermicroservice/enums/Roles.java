package ru.application.usermicroservice.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum Roles {
    FIGHTER("боец"),
    SQUAD_COMMANDER("командир отряда"),
    STAFF_OFFICER("штабник"),
    STAFF_COMMANDER("командир штаба"),
    DEFAULT_ROLES_SRSS_REALM("");

    String value;

    public static Roles of(String role) {
        String roleValue = role.toUpperCase().replaceAll("-", "_");

        return Roles.valueOf(roleValue);
    }
}
