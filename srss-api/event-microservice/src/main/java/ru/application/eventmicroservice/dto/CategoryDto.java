package ru.application.eventmicroservice.dto;

import java.util.UUID;

public record CategoryDto(
    UUID uuid,
    String name,
    Double usersCoefficient,
    Double squadsCoefficient
) { }
