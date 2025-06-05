package ru.application.usermicroservice.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import ru.application.usermicroservice.domain.AdditionalUserData;
import ru.application.usermicroservice.dto.AdditionalUserDataDto;
import ru.application.usermicroservice.mappers.AdditionalUserDataMapper;
import ru.application.usermicroservice.services.FighterAdditionalUserDataService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fighter/additional-user-data")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FighterAdditionalUserDataController {

    FighterAdditionalUserDataService fighterUserDataService;

    AdditionalUserDataMapper userDataMapper;


    @PostMapping("/create")
    public ResponseEntity<?> create(
            @AuthenticationPrincipal Jwt jwt
    ) {
        UUID userUuid = UUID.fromString(jwt.getSubject());

        AdditionalUserData userData = fighterUserDataService.create(userUuid);

        return ResponseEntity.status(HttpStatus.CREATED).body(userDataMapper.toDto(userData));
    }


    @GetMapping("/read")
    public ResponseEntity<?> read(
            @AuthenticationPrincipal Jwt jwt
    ) {
        UUID userUuid = UUID.fromString(jwt.getSubject());

        AdditionalUserData userData = fighterUserDataService.read(userUuid);

        return ResponseEntity.status(HttpStatus.OK).body(userDataMapper.toDto(userData));
    }


    @PutMapping("/update")
    public ResponseEntity<?> update(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody AdditionalUserDataDto userDataDto
    ) {
        UUID userUuid = UUID.fromString(jwt.getSubject());

        AdditionalUserData userData = fighterUserDataService.update(
                userUuid,
                userDataMapper.toDomain(userDataDto)
        );

        return ResponseEntity.status(HttpStatus.OK).body(userDataMapper.toDto(userData));
    }
}
