package ru.application.usermicroservice.services;

import ru.application.usermicroservice.domain.Squad;
import ru.application.usermicroservice.enums.SquadsSortedType;

import java.util.List;
import java.util.UUID;

public interface SquadService {
    Squad read(UUID uuid);
    Squad readByCommanderUuid(UUID userUuid);
    List<Squad> filter(SquadsSortedType sortedType, int size, int page);
}
