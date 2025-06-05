package ru.application.usermicroservice.services.implemetaions;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.application.usermicroservice.domain.Squad;
import ru.application.usermicroservice.mappers.SquadMapper;
import ru.application.usermicroservice.repositories.SquadRepository;
import ru.application.usermicroservice.services.SquadOfficerSquadService;
import ru.library.entitiesmodule.entities.user.SquadEntity;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SquadOfficerSquadServiceImpl implements SquadOfficerSquadService {

    SquadRepository squadRepository;

    SquadMapper squadMapper;


    @Override
    public Squad create(Squad squad) {
        SquadEntity squadEntity = squadRepository.save(squadMapper.toEntity(squad));

        return squadMapper.toDomain(squadEntity);
    }

    @Override
    public Squad update(Squad squad) {
        SquadEntity squadEntity = squadRepository.save(squadMapper.toEntity(squad));

        return squadMapper.toDomain(squadEntity);
    }
}
