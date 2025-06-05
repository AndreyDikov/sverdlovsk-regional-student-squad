package ru.application.eventmicroservice.mappers;

import org.mapstruct.Mapper;
import ru.application.eventmicroservice.domain.CategoryEvaluationMethod;
import ru.application.eventmicroservice.dto.CategoryEvaluationMethodDto;
import ru.library.entitiesmodule.entities.event.CategoryEvaluationMethodEntity;

@Mapper(componentModel = "spring")
public interface CategoryEvaluationMethodMapper {
    CategoryEvaluationMethod toDomain(CategoryEvaluationMethodEntity entity);
    CategoryEvaluationMethodDto toDto(CategoryEvaluationMethod categoryEvaluationMethod);
}
