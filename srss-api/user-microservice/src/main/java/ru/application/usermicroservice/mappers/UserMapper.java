package ru.application.usermicroservice.mappers;

import org.mapstruct.Mapper;
import ru.application.usermicroservice.domain.User;
import ru.application.usermicroservice.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
}
