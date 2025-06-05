package ru.application.eventmicroservice.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.application.eventmicroservice.repositories.CategoryRepository;
import ru.application.eventmicroservice.services.StaffCommanderCategoryService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StaffCommanderCategoryServiceImpl implements StaffCommanderCategoryService {

    CategoryRepository categoryRepository;


    @Override
    public void setUsersCoefficientByCategoryUuid(
            UUID categoryUuid,
            Double coefficient
    ) {
        categoryRepository.setUsersCoefficientByCategoryUuid(categoryUuid, coefficient);
    }


    @Override
    public void setSquadsCoefficientByCategoryUuid(
            UUID categoryUuid,
            Double coefficient
    ) {
        categoryRepository.setSquadsCoefficientByCategoryUuid(categoryUuid, coefficient);
    }
}
