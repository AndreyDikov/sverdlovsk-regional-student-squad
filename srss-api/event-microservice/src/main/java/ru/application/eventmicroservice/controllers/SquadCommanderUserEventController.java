package ru.application.eventmicroservice.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.application.eventmicroservice.domain.UserEvent;
import ru.application.eventmicroservice.mappers.UserEventMapper;
import ru.application.eventmicroservice.services.SquadCommanderUserEventService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/squad-commander/user-event")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SquadCommanderUserEventController {

    SquadCommanderUserEventService squadCommanderUserEventService;

    UserEventMapper userEventMapper;


    @PostMapping("/subscribe-user-to-event")
    public ResponseEntity<?> subscribeUserToEvent(
            @RequestParam("event-uuid") UUID eventUuid,
            @RequestParam("user-uuid") UUID userUuid
    ) {
        UserEvent userEvent = squadCommanderUserEventService.subscribeUserToEvent(eventUuid, userUuid);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userEventMapper.toDto(userEvent));
    }


    @PostMapping("/unsubscribe-user-from-event")
    public ResponseEntity<?> unsubscribeUserFromEvent(
            @RequestParam("event-uuid") UUID eventUuid,
            @RequestParam("user-uuid") UUID userUuid
    ) {
        squadCommanderUserEventService.unsubscribeUserFromEvent(eventUuid, userUuid);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
