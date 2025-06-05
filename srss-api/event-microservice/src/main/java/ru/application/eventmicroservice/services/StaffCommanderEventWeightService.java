package ru.application.eventmicroservice.services;

import java.util.UUID;

public interface StaffCommanderEventWeightService {
    void setWeightByUuid(UUID uuid, Double weight);
}
