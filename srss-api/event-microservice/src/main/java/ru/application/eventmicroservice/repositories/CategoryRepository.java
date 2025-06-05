package ru.application.eventmicroservice.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.library.entitiesmodule.entities.event.CategoryEntity;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {

    @Modifying
    @Transactional
    @Query(value = """
        update categories
        set users_coefficient = :coefficient
        where uuid = :categoryUuid
    """, nativeQuery = true)
    void setUsersCoefficientByCategoryUuid(UUID categoryUuid, Double coefficient);


    @Modifying
    @Transactional
    @Query(value = """
        update categories
        set squads_coefficient = :coefficient
        where uuid = :categoryUuid
    """, nativeQuery = true)
    void setSquadsCoefficientByCategoryUuid(UUID categoryUuid, Double coefficient);
}
