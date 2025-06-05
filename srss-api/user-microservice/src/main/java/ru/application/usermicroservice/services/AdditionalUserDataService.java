package ru.application.usermicroservice.services;

import ru.application.usermicroservice.domain.AdditionalUserData;

import java.util.List;
import java.util.UUID;

public interface AdditionalUserDataService {
    AdditionalUserData read(UUID userUuid);
    List<AdditionalUserData> readBySquadUuid(UUID squadUuid);
}
