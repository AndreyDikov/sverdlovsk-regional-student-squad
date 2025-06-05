package ru.application.eventmicroservice.services;

import ru.application.eventmicroservice.domain.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    Category getCategoryByUuid(UUID uuid);
    List<Category> getAllCategories();
}
