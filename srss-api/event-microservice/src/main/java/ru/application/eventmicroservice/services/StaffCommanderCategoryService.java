package ru.application.eventmicroservice.services;

import java.util.UUID;

public interface StaffCommanderCategoryService {
    void setUsersCoefficientByCategoryUuid(UUID categoryUuid, Double coefficient);
    void setSquadsCoefficientByCategoryUuid(UUID categoryUuid, Double coefficient);
}
