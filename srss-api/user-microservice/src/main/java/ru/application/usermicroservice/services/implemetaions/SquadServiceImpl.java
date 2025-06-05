package ru.application.usermicroservice.services.implemetaions;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.application.usermicroservice.domain.Squad;
import ru.application.usermicroservice.enums.SquadsSortedType;
import ru.application.usermicroservice.mappers.SquadMapper;
import ru.application.usermicroservice.repositories.SquadRepository;
import ru.application.usermicroservice.services.SquadService;
import ru.library.entitiesmodule.entities.user.SquadEntity;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SquadServiceImpl implements SquadService {

    SquadRepository squadRepository;

    SquadMapper squadMapper;


    @Override
    public Squad read(UUID uuid) {
        SquadEntity squad = squadRepository.findById(uuid).orElse(null);

        return squadMapper.toDomain(squad);
    }


    @Override
    public Squad readByCommanderUuid(UUID userUuid) {
        SquadEntity squad = squadRepository.findByCommanderUuid(userUuid);

        return squadMapper.toDomain(squad);
    }


    @Override
    public List<Squad> filter(
            SquadsSortedType sortedType,
            int size,
            int page
    ) {
        int offset = (page - 1) * size;

        List<SquadEntity> squads = squadRepository.filter(sortedType.getValue(), size, offset);

        return squads.stream()
                .map(squadMapper::toDomain)
                .toList();
    }
}
