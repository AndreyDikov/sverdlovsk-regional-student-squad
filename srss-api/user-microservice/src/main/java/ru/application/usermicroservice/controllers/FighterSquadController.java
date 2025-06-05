package ru.application.usermicroservice.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.application.usermicroservice.domain.AdditionalUserData;
import ru.application.usermicroservice.domain.Squad;
import ru.application.usermicroservice.mappers.SquadMapper;
import ru.application.usermicroservice.services.AdditionalUserDataService;
import ru.application.usermicroservice.services.FighterSquadService;
import ru.application.usermicroservice.services.SquadService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fighter/squad")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FighterSquadController {

    FighterSquadService fighterSquadService;
    AdditionalUserDataService additionalUserDataService;
    SquadService squadService;

    SquadMapper squadMapper;


    @GetMapping("/read-my-squad")
    public ResponseEntity<?> readMySquad(
            @AuthenticationPrincipal Jwt jwt
    ) {
        UUID userUuid = UUID.fromString(jwt.getSubject());

        Squad squad = squadService.readByCommanderUuid(userUuid);

        if (squad == null) {
            AdditionalUserData additionalUserData = additionalUserDataService.read(userUuid);
            squad = squadService.read(additionalUserData.getSquadUuid());
        }

        return ResponseEntity.status(HttpStatus.OK).body(squadMapper.toDto(squad));
    }


    @DeleteMapping("/leave")
    public ResponseEntity<?> leaveSquad(
            @AuthenticationPrincipal Jwt jwt
    ) {
        UUID userUuid = UUID.fromString(jwt.getSubject());

        fighterSquadService.leaveSquad(userUuid);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
