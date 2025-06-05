package ru.application.usermicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.library.entitiesmodule.entities.user.SquadEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface SquadRepository extends JpaRepository<SquadEntity, UUID> {

    @Query(value = """
        select * from squads
        where is_closed = 0
        order by :sortedType
        limit :size offset :offset
    """, nativeQuery = true)
    List<SquadEntity> filter(String sortedType, int size, int offset);


    @Query(value = """
        select * from squads
        where commander_uuid = :commanderUuid
    """, nativeQuery = true)
    SquadEntity findByCommanderUuid(UUID commanderUuid);
}
