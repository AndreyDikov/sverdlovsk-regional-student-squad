package ru.application.bff.srssbff.dto;

import java.util.UUID;

public record UserDto(
        UUID uuid,
        String email,
        String name,
        String surname,
        String role
) { }
