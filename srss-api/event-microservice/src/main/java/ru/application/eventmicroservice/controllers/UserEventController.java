package ru.application.eventmicroservice.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.application.eventmicroservice.domain.UserEvent;
import ru.application.eventmicroservice.dto.UserEventDto;
import ru.application.eventmicroservice.mappers.UserEventMapper;
import ru.application.eventmicroservice.services.UserEventService;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-event")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserEventController {

    UserEventService userEventService;

    UserEventMapper userEventMapper;


    @GetMapping("/read-users-by-event-uuid")
    public ResponseEntity<?> readUsersByEventUuid(
            @RequestParam("event-uuid") UUID eventUuid
    ) {
        List<UserEvent> usersEvent = userEventService.readUsersEventByEventUuid(eventUuid);

        List<UserEventDto> usersEventDto = usersEvent.stream()
                .map(userEventMapper::toDto)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usersEventDto);
    }


    @GetMapping("/calculate-user-score")
    public ResponseEntity<?> calculateUserScore(
        @RequestParam("user-uuid") UUID userUuid
    ) {
        Double result = userEventService.calculateUserScore(userUuid);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }


    @PostMapping("/calculate-squad-score")
    public ResponseEntity<?> calculateSquadScore(
            @RequestBody Set<UUID> squad
    ) {
        Double result = userEventService.calculateSquadScore(squad);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }
}
