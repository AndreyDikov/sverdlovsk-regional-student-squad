package ru.application.eventmicroservice.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.application.eventmicroservice.domain.Category;
import ru.application.eventmicroservice.domain.CategoryEvaluationMethod;
import ru.application.eventmicroservice.dto.CategoryDto;
import ru.application.eventmicroservice.dto.CategoryEvaluationMethodDto;
import ru.application.eventmicroservice.dto.FullCategoryEvaluationMethodDto;
import ru.application.eventmicroservice.mappers.CategoryEvaluationMethodMapper;
import ru.application.eventmicroservice.mappers.CategoryMapper;
import ru.application.eventmicroservice.services.CategoryEvaluationMethodService;
import ru.application.eventmicroservice.services.CategoryService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {

    CategoryService categoryService;
    CategoryEvaluationMethodService categoryEvaluationMethodService;

    CategoryMapper categoryMapper;
    CategoryEvaluationMethodMapper categoryEvaluationMethodMapper;


    @GetMapping("read-all")
    public ResponseEntity<?> readAll() {
        List<Category> categories = categoryService.getAllCategories();

        List<CategoryDto> categoriesDto = categories.stream()
                .map(categoryMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(categoriesDto);
    }


    @GetMapping("/get-category-evaluation-method-by-uuid")
    public ResponseEntity<?> getCategoryEvaluationMethodByUuid(
            @RequestParam("evaluation-method-uuid") UUID uuid
    ) {
        CategoryEvaluationMethod categoryEvaluationMethod = categoryEvaluationMethodService
                .getCategoryEvaluationMethodByCategoryUuid(uuid);
        CategoryEvaluationMethodDto categoryEvaluationMethodDto = categoryEvaluationMethodMapper
                .toDto(categoryEvaluationMethod);

        Category category = categoryService.getCategoryByUuid(categoryEvaluationMethodDto.categoryUuid());
        CategoryDto categoryDto = categoryMapper.toDto(category);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new FullCategoryEvaluationMethodDto(
                        categoryEvaluationMethodDto.uuid(),
                        categoryDto,
                        categoryEvaluationMethodDto.evaluationMethod()
                ));
    }


    @GetMapping("/get-all-category-evaluation-methods")
    public ResponseEntity<?> getAllCategoryEvaluationMethods() {
        List<CategoryEvaluationMethod> categoryEvaluationMethods = categoryEvaluationMethodService
                .getAllCategoryEvaluationMethods();
        List<CategoryEvaluationMethodDto> categoryEvaluationMethodsDto = categoryEvaluationMethods
                .stream()
                .map(categoryEvaluationMethodMapper::toDto)
                .toList();

        List<FullCategoryEvaluationMethodDto> fullCemDto = categoryEvaluationMethodsDto
                .stream()
                .map(cem -> {
                    Category category = categoryService.getCategoryByUuid(cem.categoryUuid());
                    return new FullCategoryEvaluationMethodDto(
                            cem.uuid(),
                            categoryMapper.toDto(category),
                            cem.evaluationMethod()
                    );
                })
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(fullCemDto);
    }
}
