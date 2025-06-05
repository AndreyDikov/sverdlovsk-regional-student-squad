package ru.application.usermicroservice.mappers;

import org.mapstruct.Mapper;
import ru.application.usermicroservice.domain.AdditionalUserData;
import ru.application.usermicroservice.dto.AdditionalUserDataDto;
import ru.library.entitiesmodule.entities.user.AdditionalUserDataEntity;

@Mapper(componentModel = "spring")
public interface AdditionalUserDataMapper {
    AdditionalUserDataDto toDto(AdditionalUserData additionalUserData);
    AdditionalUserData toDomain(AdditionalUserDataDto additionalUserDataDto);
    AdditionalUserData toDomain(AdditionalUserDataEntity additionalUserDataEntity);
}
