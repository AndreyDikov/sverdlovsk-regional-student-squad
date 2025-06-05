package ru.application.usermicroservice.dto;

import java.util.UUID;

public record FullUserDto(
    UUID uuid,
    String name,
    String surname,
    String email,
    String password,
    String role,
    AdditionalUserDataDto additionalUserData
) { }
