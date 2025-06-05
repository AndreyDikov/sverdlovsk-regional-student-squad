package ru.application.usermicroservice.mappers;

import org.mapstruct.Mapper;
import ru.application.usermicroservice.domain.Squad;
import ru.application.usermicroservice.dto.SquadDto;
import ru.library.entitiesmodule.entities.user.SquadEntity;

@Mapper(componentModel = "spring")
public interface SquadMapper {
    SquadDto toDto(Squad squad);
    Squad toDomain(SquadEntity squadEntity);
    SquadEntity toEntity(Squad squadDto);
}
