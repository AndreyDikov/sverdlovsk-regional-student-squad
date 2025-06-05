package ru.application.usermicroservice.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.application.usermicroservice.domain.AdditionalUserData;
import ru.application.usermicroservice.dto.AdditionalUserDataDto;
import ru.application.usermicroservice.mappers.AdditionalUserDataMapper;
import ru.application.usermicroservice.services.AdditionalUserDataService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/additional-user-data")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdditionalUserDataController {

    AdditionalUserDataService additionalUserDataService;

    AdditionalUserDataMapper additionalUserDataMapper;


    @GetMapping("/read")
    public ResponseEntity<?> read(
            @AuthenticationPrincipal Jwt jwt
    ) {
        UUID uuid = UUID.fromString(jwt.getSubject());

        AdditionalUserData additionalUserData = additionalUserDataService.read(uuid);

        AdditionalUserDataDto dto = additionalUserDataMapper.toDto(additionalUserData);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
}
