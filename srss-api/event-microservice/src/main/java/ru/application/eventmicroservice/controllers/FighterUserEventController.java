package ru.application.eventmicroservice.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import ru.application.eventmicroservice.domain.UserEvent;
import ru.application.eventmicroservice.mappers.UserEventMapper;
import ru.application.eventmicroservice.services.SquadCommanderUserEventService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fighter/user-event")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FighterUserEventController {

    SquadCommanderUserEventService squadCommanderUserEventService;

    UserEventMapper userEventMapper;


    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribe(
            @AuthenticationPrincipal Jwt jwt,
            @RequestParam("event-uuid") UUID eventUuid
    ) {
        UUID userUuid = UUID.fromString(jwt.getSubject());

        UserEvent userEvent = squadCommanderUserEventService.subscribeUserToEvent(eventUuid, userUuid);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userEventMapper.toDto(userEvent));
    }


    @DeleteMapping("/unsubscribe")
    public ResponseEntity<?> unsubscribe(
            @AuthenticationPrincipal Jwt jwt,
            @RequestParam("event-uuid") UUID eventUuid
    ) {
        UUID userUuid = UUID.fromString(jwt.getSubject());

        squadCommanderUserEventService.unsubscribeUserFromEvent(eventUuid, userUuid);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
