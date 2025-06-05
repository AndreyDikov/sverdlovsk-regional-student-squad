package ru.application.eventmicroservice.mappers;

import org.mapstruct.Mapper;
import ru.application.eventmicroservice.domain.Event;
import ru.application.eventmicroservice.dto.EventDto;
import ru.library.entitiesmodule.entities.event.EventEntity;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDto toDto(Event event);
    Event toDomain(EventDto eventDto);
    Event toDomain(EventEntity eventEntity);
    EventEntity toEntity(Event event);
}
