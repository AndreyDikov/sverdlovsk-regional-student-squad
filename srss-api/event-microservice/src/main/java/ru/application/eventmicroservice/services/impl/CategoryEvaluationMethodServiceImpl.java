package ru.application.eventmicroservice.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.application.eventmicroservice.domain.CategoryEvaluationMethod;
import ru.application.eventmicroservice.mappers.CategoryEvaluationMethodMapper;
import ru.application.eventmicroservice.repositories.CategoryEvaluationMethodRepository;
import ru.application.eventmicroservice.services.CategoryEvaluationMethodService;
import ru.library.entitiesmodule.entities.event.CategoryEvaluationMethodEntity;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryEvaluationMethodServiceImpl implements CategoryEvaluationMethodService {

    CategoryEvaluationMethodRepository categoryEvaluationMethodRepository;

    CategoryEvaluationMethodMapper categoryEvaluationMethodMapper;


    @Override
    public CategoryEvaluationMethod getCategoryEvaluationMethodByCategoryUuid(
            UUID categoryUuid
    ) {
        CategoryEvaluationMethodEntity categoryEvaluationMethodEntity = categoryEvaluationMethodRepository
                        .findById(categoryUuid)
                        .orElseThrow();

        return categoryEvaluationMethodMapper.toDomain(categoryEvaluationMethodEntity);
    }


    @Override
    public List<CategoryEvaluationMethod> getAllCategoryEvaluationMethods() {
        List<CategoryEvaluationMethodEntity> categoryEvaluationMethodEntities
                = categoryEvaluationMethodRepository.findAll();

        return categoryEvaluationMethodEntities.stream()
                .map(categoryEvaluationMethodMapper::toDomain)
                .toList();
    }
}
