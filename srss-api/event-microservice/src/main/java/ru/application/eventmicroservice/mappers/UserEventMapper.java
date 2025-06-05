package ru.application.eventmicroservice.mappers;

import org.mapstruct.Mapper;
import ru.application.eventmicroservice.domain.UserEvent;
import ru.application.eventmicroservice.dto.UserEventDto;
import ru.library.entitiesmodule.entities.event.UserEventEntity;

@Mapper(componentModel = "spring")
public interface UserEventMapper {
    UserEventDto toDto(UserEvent userEvent);
    UserEvent toDomain(UserEventEntity entity);
}
