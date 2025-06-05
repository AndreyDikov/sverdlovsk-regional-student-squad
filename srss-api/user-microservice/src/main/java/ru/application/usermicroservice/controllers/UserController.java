package ru.application.usermicroservice.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.application.usermicroservice.domain.AdditionalUserData;
import ru.application.usermicroservice.domain.User;
import ru.application.usermicroservice.dto.AdditionalUserDataDto;
import ru.application.usermicroservice.dto.FullUserDto;
import ru.application.usermicroservice.dto.UserDto;
import ru.application.usermicroservice.mappers.AdditionalUserDataMapper;
import ru.application.usermicroservice.mappers.UserMapper;
import ru.application.usermicroservice.services.AdditionalUserDataService;
import ru.application.usermicroservice.services.KeycloakService;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    KeycloakService keycloakService;
    AdditionalUserDataService additionalUserDataService;

    UserMapper userMapper;
    AdditionalUserDataMapper additionalUserDataMapper;


    @GetMapping("/read")
    public ResponseEntity<?> read(
            @RequestParam("user-uuid") UUID userUuid
    ) {
        User user = keycloakService.findUserByUuid(userUuid);
        AdditionalUserData additionalUserData = additionalUserDataService.read(userUuid);

        UserDto userDto = userMapper.toDto(user);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new FullUserDto(
                        userDto.uuid(),
                        userDto.name(),
                        userDto.surname(),
                        userDto.email(),
                        userDto.password(),
                        userDto.role(),
                        additionalUserDataMapper.toDto(additionalUserData)
                ));
    }


    @GetMapping("/read-all")
    public ResponseEntity<?> readAll() {
        List<User> users = keycloakService.findAllUsers();
        List<AdditionalUserData> allData = users.stream()
                .map(user -> additionalUserDataService.read(user.getUuid()))
                .toList();
        Map<UUID, AdditionalUserData> dataMap = allData.stream()
                .collect(Collectors.toMap(AdditionalUserData::getUserUuid, Function.identity()));

        List<FullUserDto> result = users.stream()
                .map(user -> {
                    AdditionalUserData add = dataMap.get(user.getUuid());
                    UserDto ud = userMapper.toDto(user);
                    return new FullUserDto(
                            ud.uuid(),
                            ud.name(),
                            ud.surname(),
                            ud.email(),
                            ud.password(),
                            ud.role(),
                            additionalUserDataMapper.toDto(add)
                    );
                })
                .toList();

        return ResponseEntity.ok(result);
    }


    @GetMapping("/read-by-squad-uuid")
    public ResponseEntity<?> readBySquadUuid(
            @RequestParam("squad-uuid") UUID squadUuid
    ) {
        Map<UUID, AdditionalUserDataDto> additionalUsersData = additionalUserDataService.readBySquadUuid(squadUuid)
                .stream()
                .map(additionalUserDataMapper::toDto)
                .collect(Collectors.toMap(AdditionalUserDataDto::userUuid, Function.identity()));

        List<FullUserDto> users = additionalUsersData.keySet()
                .stream()
                .map(keycloakService::findUserByUuid)
                .map(userMapper::toDto)
                .map(user -> new FullUserDto(
                        user.uuid(),
                        user.name(),
                        user.surname(),
                        user.email(),
                        user.password(),
                        user.role(),
                        additionalUsersData.get(user.uuid())
                    )
                )
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
}
