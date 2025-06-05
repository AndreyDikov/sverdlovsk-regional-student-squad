package ru.application.eventmicroservice.services;

import ru.application.eventmicroservice.domain.CategoryEvaluationMethod;

import java.util.List;
import java.util.UUID;

public interface CategoryEvaluationMethodService {
    CategoryEvaluationMethod getCategoryEvaluationMethodByCategoryUuid(UUID categoryUuid);
    List<CategoryEvaluationMethod> getAllCategoryEvaluationMethods();
}
