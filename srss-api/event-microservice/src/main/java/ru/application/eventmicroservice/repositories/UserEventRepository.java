package ru.application.eventmicroservice.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.library.entitiesmodule.entities.event.UserEventEntity;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UserEventRepository extends JpaRepository<UserEventEntity, UUID> {

    @Query(value = """
        select ue.*
        from users_events as ue
            join events as e on ue.event_uuid = e.uuid
        where ue.user_uuid = :userUuid
            and e.end_at > NOW();
    """, nativeQuery = true)
    List<UserEventEntity> findByUserUuid(UUID userUuid);


    @Query(value = """
        select * from users_events
        where event_uuid = :eventUuid
    """, nativeQuery = true)
    List<UserEventEntity> findByEventUuid(UUID eventUuid);


    @Query(value = """
        select * from users_events
        where user_uuid = :userUuid
            and event_uuid = :eventUuid
    """, nativeQuery = true)
    UserEventEntity findByUserUuidAndEventUuid(UUID eventUuid, UUID userUuid);


    @Query(value = """
        select coalesce(sum(sub.total_score * c.users_coefficient), 0)
        from (
            select cem.category_uuid as category_id,
                sum(ue.user_score) as total_score
            from users_events ue
            join events_weights ew
                on ue.event_weight_uuid = ew.uuid
            join categories_evaluation_methods cem
                on ew.category_evaluation_method_uuid = cem.uuid
            where ue.user_uuid = :userUuid
            group by cem.category_uuid
        ) as sub
        join categories c
            on sub.category_id = c.uuid
    """, nativeQuery = true)
    Double calculateUserScore(UUID userUuid);


    @Query(value = """
        select coalesce(
            sum(
                sub.total_score * c.squads_coefficient
            ),
            0
        ) as weighted_sum
        from (
            select cem.category_uuid as category_id,
                sum(ue.user_score) as total_score
            from users_events ue
            join events_weights ew
                on ue.event_weight_uuid = ew.uuid
            join categories_evaluation_methods cem
                on ew.category_evaluation_method_uuid = cem.uuid
            where ue.user_uuid in (:squad)
            group by cem.category_uuid
        ) as sub
        join categories c
            on sub.category_id = c.uuid
    """, nativeQuery = true)
    Double calculateSquadScore(Set<UUID> squad);


    @Modifying
    @Transactional
    @Query(value = """
        update users_events
        set user_score = user_score / :oldWeight * :newWeight
        where event_weight_uuid = :eventWeightUuid
    """, nativeQuery = true)
    void updateUserScores(UUID eventWeightUuid, Double oldWeight, Double newWeight);
}
