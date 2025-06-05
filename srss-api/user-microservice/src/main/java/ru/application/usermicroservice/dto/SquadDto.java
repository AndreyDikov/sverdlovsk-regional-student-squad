package ru.application.usermicroservice.dto;

import java.util.UUID;

public record SquadDto(
        UUID uuid,
        UUID commanderUuid,
        String name,
        String description,
        Double score,
        Boolean isClosed,
        Integer numberUsers
) { }
