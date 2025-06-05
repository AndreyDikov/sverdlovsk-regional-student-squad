package ru.application.eventmicroservice.mappers;

import org.mapstruct.Mapper;
import ru.application.eventmicroservice.domain.Category;
import ru.application.eventmicroservice.dto.CategoryDto;
import ru.library.entitiesmodule.entities.event.CategoryEntity;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toDomain(CategoryEntity entity);
    CategoryDto toDto(Category category);
}
