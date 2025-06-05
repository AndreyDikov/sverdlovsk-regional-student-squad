package ru.application.eventmicroservice.mappers;

import org.mapstruct.Mapper;
import ru.application.eventmicroservice.domain.EventWeight;
import ru.application.eventmicroservice.dto.EventWeightDto;
import ru.library.entitiesmodule.entities.event.EventWeightEntity;

@Mapper(componentModel = "spring")
public interface EventWeightMapper {
    EventWeight toDomain(EventWeightEntity entity);
    EventWeightDto toDto(EventWeight eventWeight);
}
