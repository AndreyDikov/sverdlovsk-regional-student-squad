package ru.application.eventmicroservice.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.application.eventmicroservice.domain.Category;
import ru.application.eventmicroservice.mappers.CategoryMapper;
import ru.application.eventmicroservice.repositories.CategoryRepository;
import ru.application.eventmicroservice.services.CategoryService;
import ru.library.entitiesmodule.entities.event.CategoryEntity;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    CategoryMapper categoryMapper;


    @Override
    public Category getCategoryByUuid(UUID uuid) {
        CategoryEntity categoryEntity = categoryRepository.findById(uuid).orElseThrow();

        return categoryMapper.toDomain(categoryEntity);
    }


    @Override
    public List<Category> getAllCategories() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();

        return categoryEntities.stream()
                .map(categoryMapper::toDomain)
                .toList();
    }
}
