package ru.application.eventmicroservice.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.library.entitiesmodule.entities.event.EventWeightEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventWeightRepository extends JpaRepository<EventWeightEntity, UUID> {

    @Query(value = """
        select * from events_weights
        where category_evaluation_method_uuid = :categoryEvaluationMethodUuid
    """, nativeQuery = true)
    List<EventWeightEntity> findByCategoryEvaluationMethodUuid(UUID categoryEvaluationMethodUuid);


    @Modifying
    @Transactional
    @Query(value = """
        update events_weights
        set weight = :weight
        where uuid = :uuid
    """, nativeQuery = true)
    void setWeightByUuid(UUID uuid, Double weight);


    @Query(value = """
        select ew.*
        from events_weights ew
        join categories_evaluation_methods cem
            on ew.category_evaluation_method_uuid = cem.uuid
        where cem.category_uuid = :categoryUuid
    """, nativeQuery = true)
    List<EventWeightEntity> findByCategoryUuid(UUID categoryUuid);
}
