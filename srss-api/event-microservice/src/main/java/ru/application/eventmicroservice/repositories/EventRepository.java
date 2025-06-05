package ru.application.eventmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.library.entitiesmodule.entities.event.EventEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, UUID> {

    @Query(value = """
        select * from events
        where author_uuid = :authorUuid
    """, nativeQuery = true)
    List<EventEntity> findByAuthorUuid(UUID authorUuid);


    @Query(value = """
        select e.*
        from events e
        join users_events ue
          on e.uuid = ue.event_uuid
        where ue.user_uuid = :userUuid
          and e.end_at < now()
        order by e.end_at desc
    """, nativeQuery = true)
    List<EventEntity> findPastEventsByUserUuid(UUID userUuid);


    @Query(value = """
        select * from events
        where end_at > now()
        order by :sortedType
        limit :size offset :offset
    """, nativeQuery = true)
    List<EventEntity> filter(String sortedType, int size, int offset);


    @Query(value = """
        select * from events
        where end_at > now()
        and category_evaluation_method_uuid in (
            select uuid from categories_evaluation_methods
            where category_uuid = :categoryUuid
        )
        order by :sortedType
        limit :size offset :offset
    """, nativeQuery = true)
    List<EventEntity> filter(UUID categoryUuid, String sortedType, int size, int offset);


    @Query(value = """
        select * from events
        where end_at < now()
        order by :sortedType
        limit :size offset :offset
    """, nativeQuery = true)
    List<EventEntity> history(String sortedType, int size, int offset);


    @Query(value = """
        select * from events
        where end_at < now()
        and category_evaluation_method_uuid in (
            select uuid from categories_evaluation_methods
            where category_uuid = :categoryUuid
        )
        order by :sortedType
        limit :size offset :offset
    """, nativeQuery = true)
    List<EventEntity> history(UUID categoryUuid, String sortedType, int size, int offset);
}
