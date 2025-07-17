package ru.application.eventmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.library.entitiesmodule.entities.event.CategoryEvaluationMethodEntity;

import java.util.UUID;

@Repository
public interface CategoryEvaluationMethodRepository extends JpaRepository<CategoryEvaluationMethodEntity, UUID> {

    @Query(value = """
        select * from categories_evaluation_methods
        where category_uuid = :categoryUuid
        and evaluation_method = :evaluationMethod
    """, nativeQuery = true)
    CategoryEvaluationMethodEntity findByCategoryUuidAndEvaluationMethod(
            UUID categoryUuid,
            String evaluationMethod
    );
}
