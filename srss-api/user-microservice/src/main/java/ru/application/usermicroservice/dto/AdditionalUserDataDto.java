package ru.application.usermicroservice.dto;

import ru.library.entitiesmodule.entities.user.enums.Gender;

import java.util.UUID;

public record AdditionalUserDataDto(
        UUID uuid,
        UUID userUuid,
        UUID squadUuid,
        Gender gender,
        Double score
) { }
